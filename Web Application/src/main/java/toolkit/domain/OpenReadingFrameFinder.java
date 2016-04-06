package toolkit.domain;

import toolkit.utilities.SequenceUtilities;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/22/2016.
 */
public class OpenReadingFrameFinder {
    public OpenReadingFrameResult findPotentialOrfLocations(String contig) {

        OpenReadingFrameResult frameOneResult = getOrfResultFromFrame(contig, 0, "+1");
        OpenReadingFrameResult frameTwoResult = getOrfResultFromFrame(contig.substring(1), 1, "+2");
        OpenReadingFrameResult frameThreeResult = getOrfResultFromFrame(contig.substring(2), 2, "+3");
        // TODO: This should be reflected in their character location in the contig length
        OpenReadingFrameResult frameFourResult = getOrfResultFromFrame(SequenceUtilities.getReverseSequence(contig), 0, "-1");
        OpenReadingFrameResult frameFiveResult = getOrfResultFromFrame(SequenceUtilities.getReverseSequence(contig).substring(1), 1, "-2");
        OpenReadingFrameResult frameSixResult = getOrfResultFromFrame(SequenceUtilities.getReverseSequence(contig).substring(2), 2, "-3");

        OpenReadingFrameResult result = new OpenReadingFrameResult();
        for (int i = 0; i < frameOneResult.getPotentialOrfLocations().size(); i++) {
            result.addPotentialOrfLocationToResult(frameOneResult.getPotentialOrfLocations().get(i));
        }

        for (int i = 0; i < frameTwoResult.getPotentialOrfLocations().size(); i++) {
            result.addPotentialOrfLocationToResult(frameTwoResult.getPotentialOrfLocations().get(i));
        }

        for (int i = 0; i < frameThreeResult.getPotentialOrfLocations().size(); i++) {
            result.addPotentialOrfLocationToResult(frameThreeResult.getPotentialOrfLocations().get(i));
        }

        for (int i = 0; i < frameFourResult.getPotentialOrfLocations().size(); i++) {
            result.addPotentialOrfLocationToResult(frameFourResult.getPotentialOrfLocations().get(i));
        }

        for (int i = 0; i < frameFiveResult.getPotentialOrfLocations().size(); i++) {
            result.addPotentialOrfLocationToResult(frameFiveResult.getPotentialOrfLocations().get(i));
        }

        for (int i = 0; i < frameSixResult.getPotentialOrfLocations().size(); i++) {
            result.addPotentialOrfLocationToResult(frameSixResult.getPotentialOrfLocations().get(i));
        }


        return result;
    }

    // Gets all of the Start and Stop Codons in a contig frame and then calls the method to find the ORF Locations
    // using those codons.
    private OpenReadingFrameResult getOrfResultFromFrame(String contigFrame, int frameNumberModifier, String frameIndicator) {
        OpenReadingFrameResult result;

        ArrayList<Codon> startCodons = new ArrayList<>();
        ArrayList<Codon> stopCodons = new ArrayList<>();

        for (int i = 0; i < contigFrame.length(); i += 3) {
            if ((i + 3) <= contigFrame.length()) {
                if (contigFrame.substring(i, i + 3).equalsIgnoreCase("ATG")) {
                    startCodons.add(new Codon("ATG", i + frameNumberModifier));
                }
            }
        }

        for (int i = 0; i < contigFrame.length(); i += 3) {
            if ((i + 3) <= contigFrame.length()) {
                String nextCodonInFrame = contigFrame.substring(i, i + 3);
                if (stopCodonDetected(nextCodonInFrame)) {
                    stopCodons.add(new Codon(nextCodonInFrame, i + frameNumberModifier));
                }
            }
        }

        result = constructOrfsFromCodons(startCodons, stopCodons, contigFrame, frameNumberModifier, frameIndicator);
        return result;
    }

    // Return if the provided string is a Stop Codon.
    private boolean stopCodonDetected(String codon) {
        boolean stopCodonDetected = false;
        if (codon.equalsIgnoreCase("TAG")) {
            stopCodonDetected = true;
        }
        if (codon.equalsIgnoreCase("TGA")) {
            stopCodonDetected = true;
        }
        if (codon.equalsIgnoreCase("TAA")) {
            stopCodonDetected = true;
        }

        return stopCodonDetected;
    }

    // Through using the Start and Stop Codons list of a contig, finds the longest ORF Locations and returns the results
    private OpenReadingFrameResult constructOrfsFromCodons(ArrayList<Codon> startCodons, ArrayList<Codon> stopCodons,
                                                           String contig, int frameNumberModifier, String frameIndicator) {
        OpenReadingFrameResult result = new OpenReadingFrameResult();

        // Deal with each and every Start and Stop Codon to build our ORF Locations
        while(startCodons.size() > 0 && stopCodons.size() > 0){
            // Remove every Stop Codon that comes before our current first known Start Codon.
            for (int i = 0; i < stopCodons.size(); i++) {
                Codon stopCodon = stopCodons.get(i);
                if(stopCodon.getContigStartIndex() < startCodons.get(0).getContigStartIndex()){
                    stopCodons.remove(stopCodon);
                }
            }

            // Remove every Start Codon that comes after the current Start Codon but before the next Stop Codon
            for(int i = 0; i < startCodons.size(); i++){
                if(startCodons.size() > 1){
                    Codon nextCodon = startCodons.get(1);
                    if(nextCodon.getContigStartIndex() < stopCodons.get(0).getContigStartIndex()){
                        startCodons.remove(nextCodon);
                    }
                }
            }

            // If there is more than one Start Codon left, we need to remove any Stop Codons between the current Start
            // Codon and the last Stop Codon before the next Start Codon
            if(startCodons.size() > 1){
                for (int i = 0; i < stopCodons.size(); i++) {
                    Codon stopCodon = stopCodons.get(i);
                    // Only looking at those Stop Codons that come before the next Start Codon
                    if(stopCodon.getContigStartIndex() < startCodons.get(1).getContigStartIndex()){
                        if(stopCodons.size() > 1){
                            // If there is another Stop Codon after the current known one, and it is also before the next
                            // Start Codon after the current, we know we're safe to remove the current Stop Codon as the
                            // next Stop Codon makes a longer ORF before the next ORF.
                            if(stopCodons.get(1).getContigStartIndex() < startCodons.get(1).getContigStartIndex()){
                                stopCodons.remove(stopCodon);
                            }
                        }
                    }
                }
            }

            // If at the end of all of this there is still an ORF to be made, construct it, then remove the components.
            if(startCodons.size() > 0 && stopCodons.size() > 0){
                // Since we're sweeping through the lists in passes, make sure the Start and Stop Codons are somewhat aligned
                if(startCodons.get(0).getContigStartIndex() < stopCodons.get(0).getContigStartIndex()){
                    // If there is only one Start Codon left, we only need to use the last Stop Codon. Removing the final
                    // Start Codon will end this loop even if we leave all Stop Codons in the list.
                    OpenReadingFrameLocation newLocation;
                    if(startCodons.size() == 1){
                        newLocation = createCompletedOrf(contig, startCodons.get(0), stopCodons.get(stopCodons.size()-1),
                                frameNumberModifier, frameIndicator);

                    } else {
                        newLocation = createCompletedOrf(contig, startCodons.get(0), stopCodons.get(0), frameNumberModifier,
                                frameIndicator);
                        stopCodons.remove(0);
                    }
                    result.addPotentialOrfLocationToResult(newLocation);
                    startCodons.remove(0);
                }
            }
        }

        return result;
    }

    // Assembles an OpenReadingFrameLocation with the required components.
    private OpenReadingFrameLocation createCompletedOrf(String contig, Codon startCodon, Codon stopCodon,
                                                        int frameNumberModifier, String frameIndicator) {
        OpenReadingFrameLocation completedOrf = new OpenReadingFrameLocation(
                contig.substring((startCodon.getContigStartIndex() - frameNumberModifier), stopCodon.getContigStartIndex() + (3 - frameNumberModifier)),
                startCodon.getContigStartIndex(), stopCodon.getContigStartIndex() + 2, frameIndicator);

        return completedOrf;
    }
}
