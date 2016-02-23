package com.metagenomequalitytoolkit.mmp;

/**
 * Created by James Euesden on 2/22/2016.
 */
public class OpenReadingFrameFinder {
    public OpenReadingFrameResult findPotentialGenomeEncodingRegions(String contig){
        if(contig.contains("ATG")){
            String currentPotentialRegion = contig.substring(contig.indexOf("ATG"));
            // Ends on TAG, TAA, TGA
            if(currentPotentialRegion.contains("TAG")){
                System.out.println(currentPotentialRegion.substring(0, currentPotentialRegion.indexOf("TAG") + 3));
            }
            if(currentPotentialRegion.contains("TGA")){
                System.out.println(currentPotentialRegion.substring(0, currentPotentialRegion.indexOf("TGA") + 3));
            }
            if(currentPotentialRegion.contains("TAA")){
                System.out.println(currentPotentialRegion.substring(0, currentPotentialRegion.indexOf("TAA") + 3));
            }

        }

        // Just to see how many times ATG comes up.... Remove this
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

        return new OpenReadingFrameResult();
    }
}
