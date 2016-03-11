package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/22/2016.
 */
public class OpenReadingFrameFinder {
    public OpenReadingFrameResult findPotentialOrfLocations(String contig){
        OpenReadingFrameResult result = new OpenReadingFrameResult();
        if(contig.contains("ATG")){
            int startCodon = contig.indexOf("ATG");
            String currentPotentialRegion = contig.substring(startCodon);

            System.out.println("Potential ORF found with stop codons:");
            // Ends on TAG, TAA, TGA
            if(currentPotentialRegion.contains("TAG")){
                int stopCodon = currentPotentialRegion.indexOf("TAG") + 3;
                String orfCharacters = currentPotentialRegion.substring(0, stopCodon);
                if(orfCharacters.length() - 3 > 6){
                    result.addPotentialOrfLocationToResult(new OpenReadingFrameLocation(orfCharacters, startCodon, stopCodon));
                    System.out.println("TAG stop codon: " + currentPotentialRegion.substring(0, stopCodon));
                }
            }
            if(currentPotentialRegion.contains("TGA")){
                int stopCodon = currentPotentialRegion.indexOf("TGA") + 3;
                String orfCharacters = currentPotentialRegion.substring(0, stopCodon);
                if(orfCharacters.length() - 3 > 6){
                    result.addPotentialOrfLocationToResult(new OpenReadingFrameLocation(orfCharacters, startCodon, stopCodon));
                    System.out.println("TGA stop codon: " + currentPotentialRegion.substring(0, stopCodon));
                }
            }
            if(currentPotentialRegion.contains("TAA")){
                int stopCodon = currentPotentialRegion.indexOf("TAA") + 3;
                String orfCharacters = currentPotentialRegion.substring(0, stopCodon);
                if(orfCharacters.length() - 3 > 6){
                    result.addPotentialOrfLocationToResult(new OpenReadingFrameLocation(orfCharacters, startCodon, stopCodon));
                    System.out.println("TAA stop codon: " + currentPotentialRegion.substring(0, stopCodon));
                }
            }
        }

        // Just to visually see how many times ATG comes up.... Remove this
        int count = 0;
        int lastCheck = 0;
        String currentPotentialRegion = contig;
        while(count == lastCheck){
            if(currentPotentialRegion.contains("ATG")){
                currentPotentialRegion = currentPotentialRegion.substring(currentPotentialRegion.indexOf("ATG") + 3);
                count++;
            }
            lastCheck++;
        }
        System.out.println("Number of ATG in sequence: " + count + " (last check: " + lastCheck + ")");

        return result;
    }
}
