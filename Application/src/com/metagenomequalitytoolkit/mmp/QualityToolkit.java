package com.metagenomequalitytoolkit.mmp;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {

    protected void run(){
        FastaReader reader = new FastaReader();
        reader.readFile("./assets/contig.1274754.fa");
        //reader.readFile("./assets/contigs.fa");
    }
}
