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

    public GcResult readFile(String fileLocation){
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
                            qualityAssess(currentContig);
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
            return qualityAssess(currentContig);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private GcResult qualityAssess(ContiguousRead currentContig){
        GcResult gcResult = gcContentCounter.countGcContent(currentContig.getContigContext(), 300);
        OpenReadingFrameResult orfResult = orfFinder.findPotentialGenomeEncodingRegions(currentContig.getContigContext());
        System.out.println("Potential ORF at character " + orfResult.getStartIndex() + " sequence: " + orfResult.getPotentialOrf());

        System.out.println("Contig: " + currentContig.getContigInformation() + " Length: " + currentContig.getContigContext().length());
        System.out.println(currentContig.getContigContext());
        System.out.println("window\tgccontent");
        for(int i=0; i < gcResult.getGCContentPercentages().size(); i++){
            System.out.println((i+1) + "\t" + gcResult.getGCContentPercentages().get(i));
        }

        return gcResult;
    }
}
