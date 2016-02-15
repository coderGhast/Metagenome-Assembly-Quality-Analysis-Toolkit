package com.metagenomequalitytoolkit.mmp;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/15/2016.
 */
public class GcResult {
    private ArrayList<GcWindow> gcWindows = new ArrayList<>();
    private int windowSize;

    public GcResult(int windowSize){
        this.windowSize = windowSize;
    }

    public void setGcWindows(ArrayList<GcWindow> gcWindows) {
        this.gcWindows = gcWindows;
    }

    public int getWindowSize(){
        return windowSize;
    }

    public int getTotalCount(){
        int totalCount = 0;
        for (GcWindow gcWindow: gcWindows) {
            totalCount += gcWindow.getWindowContentSize();
        }

        return totalCount;
    }

    public int getTotalGCount(){
        int totalGCount = 0;
        for (GcWindow gcWindow: gcWindows) {
            totalGCount += gcWindow.getGCount();
        }

        return totalGCount;
    }

    public int getTotalCCount(){
        int totalCCount = 0;
        for (GcWindow gcWindow: gcWindows) {
            totalCCount += gcWindow.getCCount();
        }

        return totalCCount;
    }
}
