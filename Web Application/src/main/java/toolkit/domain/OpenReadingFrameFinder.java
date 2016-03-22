package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/22/2016.
 */
public class OpenReadingFrameFinder {
    public OpenReadingFrameResult findPotentialOrfLocations(String contig){

        OpenReadingFrameResult frameOneResult = getOrfResultFromFrame(contig);
        OpenReadingFrameResult frameTwoResult = getOrfResultFromFrame(contig.substring(1));
        OpenReadingFrameResult frameThreeResult = getOrfResultFromFrame(contig.substring(2));
        //TODO: Convert contig characters to base pairs, reverse the sequence and then carry out 3 frame checks again

        OpenReadingFrameResult result = new OpenReadingFrameResult();
        for(int i=0; i < frameOneResult.getPotentialOrfLocations().size(); i++){
            result.addPotentialOrfLocationToResult(frameOneResult.getPotentialOrfLocations().get(i));
        }

        for(int i=0; i < frameTwoResult.getPotentialOrfLocations().size(); i++){
            result.addPotentialOrfLocationToResult(frameTwoResult.getPotentialOrfLocations().get(i));
        }

        for(int i=0; i < frameThreeResult.getPotentialOrfLocations().size(); i++){
            result.addPotentialOrfLocationToResult(frameThreeResult.getPotentialOrfLocations().get(i));
        }


        return result;
    }

    private OpenReadingFrameResult getOrfResultFromFrame(String contigFrame){
        OpenReadingFrameResult result;

        ArrayList<Codon> startCodons = new ArrayList<>();
        ArrayList<Codon> stopCodons = new ArrayList<>();

        for(int i = 0; i < contigFrame.length(); i+=3){
            if((i + 3) <= contigFrame.length()){
                if(contigFrame.substring(i, i+3).equalsIgnoreCase("ATG")){
                    startCodons.add(new Codon("ATG", i));
                }
            }
        }

        for(int i = 0; i < contigFrame.length(); i+=3){
            if((i + 3) <= contigFrame.length()) {
                String nextCodonInFrame = contigFrame.substring(i, i + 3);
                if (stopCodonDetected(nextCodonInFrame)) {
                    stopCodons.add(new Codon(nextCodonInFrame, i));
                }
            }
        }

        result = constructOrfsFromCodons(startCodons, stopCodons, contigFrame);
        return result;
    }

    private boolean stopCodonDetected(String codon){
        boolean stopCodonDetected = false;
        if(codon.equalsIgnoreCase("TAG")){
            stopCodonDetected = true;
        }
        if(codon.equalsIgnoreCase("TGA")){
            stopCodonDetected = true;
        }
        if(codon.equalsIgnoreCase("TAA")){
            stopCodonDetected = true;
        }

        return stopCodonDetected;
    }

    private OpenReadingFrameResult constructOrfsFromCodons(ArrayList<Codon> startCodons, ArrayList<Codon> stopCodons,
                                                           String contig){
        OpenReadingFrameResult result = new OpenReadingFrameResult();

        if(startCodons.size() > 0 && stopCodons.size() > 0){
            for(int i=0; i<startCodons.size(); i++){
                Codon earliestStartCodon = startCodons.get(i);
                if(startCodons.size() > (i+1)){
                    Codon nextCodon = startCodons.get(i + 1);
                    for (Codon currentStopCodon:stopCodons) {
                        if(currentStopCodon.getContigStartIndex() > earliestStartCodon.getContigStartIndex()
                                && currentStopCodon.getContigStartIndex() < nextCodon.getContigStartIndex()){
                            result.addPotentialOrfLocationToResult(createCompletedOrf(contig, earliestStartCodon, currentStopCodon));
                        }
                    }
                } else {
                    Codon lastStopCodon = stopCodons.get(startCodons.size()-1);
                    result.addPotentialOrfLocationToResult(createCompletedOrf(contig, earliestStartCodon, lastStopCodon));
                }
            }
        }
        return result;
    }

    private OpenReadingFrameLocation createCompletedOrf(String contig, Codon startCodon, Codon stopCodon){
        OpenReadingFrameLocation completedOrf = new OpenReadingFrameLocation(
                contig.substring(startCodon.getContigStartIndex(), stopCodon.getContigStartIndex() +3),
                startCodon.getContigStartIndex(), stopCodon.getContigStartIndex() +3);

        return completedOrf;
    }
}
