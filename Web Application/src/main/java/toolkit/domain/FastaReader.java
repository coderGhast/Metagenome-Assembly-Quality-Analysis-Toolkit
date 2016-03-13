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
    private OpenReadingFrameFinder orfFinder = new OpenReadingFrameFinder();

    public GcResult readFile(String fileLocation, int windowSize){
        StringBuilder fastaFileContentBuilder = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileLocation));
            ContiguousRead currentContig = new ContiguousRead();

            String line;
            while((line = in.readLine())!= null){
                if(line.startsWith(">")) {
                    if(currentContig.getContigInformation() != null){
                        currentContig.setContigContext(fastaFileContentBuilder.toString());
                        if(fastaFileContentBuilder.length() > 101){
                            qualityAssess(currentContig, windowSize);
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
            return qualityAssess(currentContig, windowSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private GcResult qualityAssess(ContiguousRead currentContig, int windowSize){
        GcResult gcResult = gcContentCounter.countGcContent(currentContig.getContigContext(), windowSize);
        OpenReadingFrameResult orfResult = new OpenReadingFrameFinder().findPotentialOrfLocations(currentContig.getContigContext());

        System.out.println("Contig: " + currentContig.getContigInformation());
        System.out.println(currentContig.getContigContext());

        return gcResult;
    }
}
