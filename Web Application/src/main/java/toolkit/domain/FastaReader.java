package toolkit.domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class FastaReader{
    private GcContentCounter gcContentCounter = new GcContentCounter();

    public GcResult readFile(String fileLocation, int windowSize, int contigLengthThreshold, int orfLengthThrshold){
        StringBuilder fastaFileContentBuilder = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileLocation));
            ContiguousRead currentContig = new ContiguousRead();

            String line;
            while((line = in.readLine())!= null){
                if(line.startsWith(">")) {
                    if(currentContig.getContigInformation() != null){
                        currentContig.setContigContext(fastaFileContentBuilder.toString());
                        if(fastaFileContentBuilder.length() > contigLengthThreshold){
                            qualityAssess(currentContig, windowSize, orfLengthThrshold);
                        } else {
                            System.out.println("Skipped: " + currentContig.getContigInformation());
                        }
                        fastaFileContentBuilder.setLength(0);
                    }
                    currentContig = new ContiguousRead();
                    currentContig.setContigInformation(line);
                } else {
                    FastaLineCleaner.cleanLine(line);
                    fastaFileContentBuilder.append(line);
                }
            }
            // Assess the final contig
            currentContig.setContigContext(fastaFileContentBuilder.toString());
            return qualityAssess(currentContig, windowSize, orfLengthThrshold);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private GcResult qualityAssess(ContiguousRead currentContig, int windowSize, int orfLengthThreshold){
        GcResult gcResult = gcContentCounter.countGcContent(currentContig.getContigContext(), windowSize);
        OpenReadingFrameResult orfResult = new OpenReadingFrameFinder().findPotentialOrfLocations(currentContig.getContigContext());
        orfResult.removeLowerThanThresholdOrfLocations(orfLengthThreshold);
        System.out.println(orfResult.getPotentialOrfLocations().size());


        return gcResult;
    }
}
