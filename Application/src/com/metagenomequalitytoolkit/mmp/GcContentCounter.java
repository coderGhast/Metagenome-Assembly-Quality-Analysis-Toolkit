package com.metagenomequalitytoolkit.mmp;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/15/2016.
 */
public class GcContentCounter {
    public GcResult countGcContent(String assembly, int windowSize){
        GcResult gcResult = new GcResult(windowSize);
        ArrayList<GcWindow> gcWindows = splitIntoWindows(assembly, windowSize);
        gcResult.setGcWindows(gcWindows);

        return gcResult;
    }

    private ArrayList<GcWindow> splitIntoWindows(String assembly, int windowSize){
        ArrayList<GcWindow> windowedAssembly = new ArrayList<>();
        for(int i=0; i < (assembly.length()); i += windowSize){
            for(int j=0; j < windowSize; j++){
                if(i + j < assembly.length()){
                    windowedAssembly.add(new GcWindow(assembly.substring(i, i + j)));
                }
            }
        }
        return windowedAssembly;
    }
}
