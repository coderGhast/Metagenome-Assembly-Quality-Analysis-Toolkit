package toolkit.domain;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {

    public GcResult run(){
        FastaReader reader = new FastaReader();
        return reader.readFile("./src/main/resources/static/contig.1274754.fa");
        //reader.readFile("./src/main/resources/contigs.fa");
    }
}
