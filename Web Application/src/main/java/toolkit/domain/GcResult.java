package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/15/2016.
 */
public class GcResult {
    private int windowSize;
    private double gcMean;
    private boolean meanCalculated;
    private ArrayList<Double> _percentages;

    public GcResult(int windowSize){
        this.windowSize = windowSize;
    }

    public int getWindowSize(){
        return windowSize;
    }

    public double mean(){
        if(!meanCalculated){
            double total = 0;
            ArrayList<Double> percentages = getGCContentPercentages();
            for (Double gcContentPercentage: percentages) {
                total += gcContentPercentage;
            }
            gcMean = total / percentages.size();
            meanCalculated = true;
            return gcMean;
        }

        return gcMean;
    }

    public void setGcContentPercentages(ArrayList<Double> percentages){
        this._percentages = percentages;
    }

    public ArrayList<Double> getGCContentPercentages(){
        return this._percentages;
    }
}
