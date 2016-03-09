package toolkit.domain;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {

    public GcResult run(String fileName, int gcWindowSize){
        FastaReader reader = new FastaReader();
        return reader.readFile(fileName, gcWindowSize);
        //reader.readFile("./src/main/resources/contigs.fa");
    }
}
