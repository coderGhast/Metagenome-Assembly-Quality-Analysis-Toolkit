package toolkit;

import org.apache.tomcat.jni.Directory;

import java.io.File;
import java.io.IOException;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {

    protected void run(){
        FastaReader reader = new FastaReader();
        reader.readFile("./src/main/resources/contig.1274754.fa");
        //reader.readFile("./src/main/resources/contigs.fa");
    }
}
