package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/15/2016.
 */
public class GcResult {
    private int _windowSize;
    private double _gcMean;
    private boolean _meanCalculated;
    private ArrayList<Double> _percentages;

    public GcResult(int windowSize){
        this._windowSize = windowSize;
    }

    public int getWindowSize(){
        return _windowSize;
    }

    public double mean(){
        if(!_meanCalculated){
            double total = 0;
            ArrayList<Double> percentages = getGCContentPercentages();
            for (Double gcContentPercentage: percentages) {
                total += gcContentPercentage;
            }
            _gcMean = total / percentages.size();
            _meanCalculated = true;
            return _gcMean;
        }

        return _gcMean;
    }

    public void setGcContentPercentages(ArrayList<Double> percentages){
        this._percentages = percentages;
    }

    public ArrayList<Double> getGCContentPercentages(){
        return this._percentages;
    }
}
