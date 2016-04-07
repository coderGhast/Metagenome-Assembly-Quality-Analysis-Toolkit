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

    public QualitySummary readUserContent(UserParameters params){
        ContiguousRead currentContig = new ContiguousRead();

        String contig = params.getUserContent().trim();
        currentContig.setContigInformation(contig.substring(0, contig.indexOf('\n')));
        contig = contig.substring(contig.indexOf('\n') + 1, contig.length() -1);
        currentContig.setContigContext(contig.replace("\n", "").replace("\r", ""));

        return qualityAssess(currentContig,params.getGcWindowSize(), params.getOrfLengthThreshold());
    }

    public QualitySummary readFile(UserParameters params){
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
                    fastaFileContentBuilder.append(line.trim());
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
    private QualitySummary qualityAssess(ContiguousRead currentContig, int windowSize, int orfLengthThreshold){
        QualitySummary summary = new QualitySummary();

        GcResult gcResult = gcContentCounter.countGcContent(currentContig.getContigContext(), windowSize);
        summary.addGcResult(gcResult);

        OpenReadingFrameResult orfResult = new OpenReadingFrameFinder().findPotentialOrfLocations(currentContig.getContigContext());
        orfResult.removeLowerThanThresholdOrfLocations(orfLengthThreshold);

        summary.addOrfResult(orfResult);


        return summary;
    }
}
