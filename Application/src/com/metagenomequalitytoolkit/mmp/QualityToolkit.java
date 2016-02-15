package com.metagenomequalitytoolkit.mmp;

/**
 * Created by James Euesden on 2/10/2016.
 */
public class QualityToolkit {

    protected void run(){
        String assembly = new FastaReader().readFile("./assets/contig.1274754.fa");

        GcContentCounter gcContentCounter = new GcContentCounter();
        GcResult result = gcContentCounter.countGcContent(assembly, 100);

        System.out.println("GC Percentages: ");
        for(int i=0; i < result.getGCContentPercentages().size(); i++){
            System.out.println(result.getGCContentPercentages().get(i) + "%");
        }
    }
}
