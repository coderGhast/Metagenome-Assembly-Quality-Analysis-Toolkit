package toolkit.domain;

import toolkit.utilities.SequenceUtilities;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/22/2016.
 *
 * Searches through a provided String (a contiguous read) to find areas of potential gene encoding regions
 * (Open Reading Frames - ORF) within the open reading frames (1 - 6) and creates OpenReadingFrameLocations
 * in an OpenReadingFrameResult.
 */
public class OpenReadingFrameFinder {
    /**
     * Entry point in finding any ORF Locations.Creates a new OpenReadingFrameResult object to be populated and
     * creates the frames from the contiguous read to be inspected, then calls to find ORF Locations in each of the
     * frames. Each call returns an OpenReadingFrameResult based on just that Frame. The final returned ORFResult is
     * also an OpenReadingFrameResult class, with each of the ORF Locations from each frame combined within it.
     * @param contiguousRead The contiguous read characters to be searched through.
     * @return An OpenReadingFrameResult containing all of the ORF locations found in the provided contiguous read.
     */
    public OpenReadingFrameResult findPotentialOrfLocations(String contiguousRead) {
        OpenReadingFrameResult result = new OpenReadingFrameResult();
        String reverseSequence = SequenceUtilities.getReverseSequence(contiguousRead);
        for(int i = 0; i < 3; i++){
            // Simultaneously calls to look in the forward and reverse frames, using the loop iterator to modify the
            // frame indicator. (e.g. i = 0 -> Frame 1, i + 3 = 3 -> Frame 4)
            OpenReadingFrameResult frameResult = getOrfResultFromFrame(contiguousRead.substring(i), i, i);
            OpenReadingFrameResult reverseFrameResult = getOrfResultFromFrame(reverseSequence.substring(i), i, i+3);
            reverseFrameResult = correctReverseFrameIndexes(reverseFrameResult, contiguousRead.length());

            result.addPotentialOrfLocationListToResult(frameResult.getPotentialOrfLocations());
            result.addPotentialOrfLocationListToResult(reverseFrameResult.getPotentialOrfLocations());
        }
        return result;
    }

    /**
     * For individual frames, finds the Start and Stop Codons from within that frame and then calls to a method to use
     * those to find the OpenReadingFrameLocations within the contiguous read frame provided.
     * @param contigFrame The characters of the frame from the contiguous read.
     * @param frameNumberModifier The frame number modifier (used for correctly placing the index of an ORF location).
     * @param frameIndicator Indicates what frame an ORF may be from, minus 1 (e.g. Frame 1 is indicator 0).
     * @return Returns the results of carrying out an ORF Location search on this Frame.
     */
    private OpenReadingFrameResult getOrfResultFromFrame(String contigFrame, int frameNumberModifier, int frameIndicator) {
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

    /**
     * Looks if the String provided matches a Stop Codon.
     * @param codon The provided characters, expected to be three characters in length.
     * @return A boolean of whether the characters provided are actually a Stop Codon or not,
     * doesn't matter which it is.
     */
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

    /**
     * Using the lists of Start and Stop Codons in a frame, looks to stitch together ORF Locations.
     * @param startCodons The list of Start Codons.
     * @param stopCodons The list of Stop Codons.
     * @param contigFrame The contiguous read data from this frame.
     * @param frameNumberModifier The frame number to modify the indexes of any ORF Location to match the real
     *                            contiguous read.
     * @param frameIndicator The frame number (minus 1).
     * @return Returns the result of any ORF Locations found within this frame, using the Start and Stop Codons provided.
     */
    private OpenReadingFrameResult constructOrfsFromCodons(ArrayList<Codon> startCodons, ArrayList<Codon> stopCodons,
                                                           String contigFrame, int frameNumberModifier, int frameIndicator) {
        OpenReadingFrameResult result = new OpenReadingFrameResult();

        // Deal with each and every Start and Stop Codon to build our ORF Locations until we can't possibly make a new
        // ORF Location as we run out of either Start or Stop points.
        while(startCodons.size() > 0 && stopCodons.size() > 0){
            // Remove every Stop Codon that comes before our current first known Start Codon.
            for (int i = 0; i < stopCodons.size(); i++) {
                Codon stopCodon = stopCodons.get(i);
                if(stopCodon.getContigStartIndex() < startCodons.get(0).getContigStartIndex()){
                    stopCodons.remove(stopCodon);
                }
            }

            // Remove every Start Codon that comes after the current Start Codon but before the next Stop Codon
            if(stopCodons.size() > 0){
                for(int i = 0; i < startCodons.size(); i++){
                    if(startCodons.size() > 1){
                        Codon nextCodon = startCodons.get(1);
                        if(nextCodon.getContigStartIndex() < stopCodons.get(0).getContigStartIndex()){
                            startCodons.remove(nextCodon);
                        }
                    }
                }
            }

            // If at the end of all of this there is still an ORF to be made, construct it, then remove the components.
            if(startCodons.size() > 0 && stopCodons.size() > 0){
                // Since we're sweeping through the lists in passes, make sure the Start and Stop Codons are somewhat aligned
                if(startCodons.get(0).getContigStartIndex() < stopCodons.get(0).getContigStartIndex()){
                    // We should now have the first Start Codon and the first Stop Codon to construct a full
                    // ORF and being the cycle again
                    OpenReadingFrameLocation newLocation = createCompletedOrf(contigFrame, startCodons.get(0), stopCodons.get(0), frameNumberModifier,
                                frameIndicator);
                    stopCodons.remove(0);
                    startCodons.remove(0);
                    result.addPotentialOrfLocationToResult(newLocation);
                }
            }
        }

        return result;
    }

    // Assembles an OpenReadingFrameLocation with the required components.

    /**
     * Assembles an ORF Location with the provided components, stitching it together, getting the characters between
     * the provided start and stop codon.
     * @param contigFrame The characters of the contiguous read from the frame in question.
     * @param startCodon The Codon that marks the start of the ORF Location.
     * @param stopCodon The Codon that marks the end of the ORF Location.
     * @param frameNumberModifier The modified to the ORF Location indexes.
     * @param frameIndicator The frame indicator to identify which frame the constructed ORF Location is from.
     * @return A completed OpenReadingFrameLocation.
     */
    private OpenReadingFrameLocation createCompletedOrf(String contigFrame, Codon startCodon, Codon stopCodon,
                                                        int frameNumberModifier, int frameIndicator) {
        OpenReadingFrameLocation completedOrf = new OpenReadingFrameLocation(
                contigFrame.substring((startCodon.getContigStartIndex() - frameNumberModifier), stopCodon.getContigStartIndex() + (3 - frameNumberModifier)),
                startCodon.getContigStartIndex(), stopCodon.getContigStartIndex() + 3, frameIndicator);

        return completedOrf;
    }

    /**
     * Corrects the indexes of a reverse frame. Since the frame indexes will be reversed when the ORF Locations are
     * constructed, the indexes are corrected to display where the actual 'start' and 'stop' locations are from when
     * the contiguous is in the original direction.
     * @param reverseFrameResult The OpenReadingFrameResult of a reverse frame.
     * @param contigLength The length of the original and full contiguous read.
     * @return The OpenReadingFrameResult with corrected indexes.
     */
    private OpenReadingFrameResult correctReverseFrameIndexes(OpenReadingFrameResult reverseFrameResult, int contigLength){
        ArrayList<OpenReadingFrameLocation> orfLocations = reverseFrameResult.getPotentialOrfLocations();
        OpenReadingFrameResult correctedResult = new OpenReadingFrameResult();
        for(int i=0; i < orfLocations.size(); i++){
            OpenReadingFrameLocation currentLocation = orfLocations.get(i);
            OpenReadingFrameLocation correctedLocation = new OpenReadingFrameLocation(currentLocation.getOrfCharacters(),
                    contigLength - currentLocation.getOrfStartIndex(),
                    contigLength - currentLocation.getOrfStopIndex(),
                    currentLocation.getFrameIndicator());
            correctedResult.addPotentialOrfLocationToResult(correctedLocation);
        }

        return correctedResult;
    }
}
