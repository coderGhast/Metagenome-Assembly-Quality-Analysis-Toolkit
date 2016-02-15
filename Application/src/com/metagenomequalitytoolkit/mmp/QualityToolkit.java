package com.metagenomequalitytoolkit.mmp;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {

    protected void run(){
        String assembly = new FastaReader().readFile("./assets/contig.1274754.fa");
        System.out.println(assembly);

        GcContentCounter gcContentCounter = new GcContentCounter();
        GcResult result = gcContentCounter.countGcContent(assembly, 1000);

        System.out.println("Total count: " + result.getTotalCount() + " C count: " + result.getTotalCCount() + " G count: " + result.getTotalGCount());
    }
}
