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

    public GcResult readUserContent(UserParameters params){
        StringBuilder fastaFileContentBuilder = new StringBuilder();
        ContiguousRead currentContig = new ContiguousRead();

        String contig = params.getUserContent();
        String contigContent = contig.replace('\n', ' ');

        currentContig.setContigContext(fastaFileContentBuilder.toString());
        System.out.println(currentContig.getContigInformation());
        System.out.print(contigContent);

        return new GcResult(1);
       //return qualityAssess(currentContig,params.getGcWindowSize(), params.getOrfLengthThreshold());
    }

    public GcResult readFile(UserParameters params){
        StringBuilder fastaFileContentBuilder = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new FileReader(params.getFileName()));
            ContiguousRead currentContig = new ContiguousRead();

            String line;
            while((line = in.readLine())!= null){
                if(line.startsWith(">")) {
                    if(currentContig.getContigInformation() != null){
                        currentContig.setContigContext(fastaFileContentBuilder.toString());
                        if(fastaFileContentBuilder.length() > params.getContigLengthThreshold()){
                            qualityAssess(currentContig, params.getGcWindowSize(), params.getOrfLengthThreshold());
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
            return qualityAssess(currentContig, params.getGcWindowSize(), params.getOrfLengthThreshold());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: Should just return 'QualityResult', with GC Result as part of it, not GcResult as returned object
    private GcResult qualityAssess(ContiguousRead currentContig, int windowSize, int orfLengthThreshold){
        GcResult gcResult = gcContentCounter.countGcContent(currentContig.getContigContext(), windowSize);
        OpenReadingFrameResult orfResult = new OpenReadingFrameFinder().findPotentialOrfLocations(currentContig.getContigContext());
        orfResult.removeLowerThanThresholdOrfLocations(orfLengthThreshold);
        System.out.println(orfResult.getPotentialOrfLocations().size());


        return gcResult;
    }
}
