package toolkit.domain;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {

    public QualityToolkit(){

    }

    public String test = "helloloalal";

    public GcResult run(){
        FastaReader reader = new FastaReader();
        return reader.readFile("./src/main/resources/contig.1274754.fa");
        //reader.readFile("./src/main/resources/contigs.fa");
    }
}
