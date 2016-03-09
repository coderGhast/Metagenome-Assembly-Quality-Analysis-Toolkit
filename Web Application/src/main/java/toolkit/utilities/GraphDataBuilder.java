package toolkit.utilities;

import toolkit.domain.GcResult;
import toolkit.domain.GcResultViewData;
import toolkit.domain.UserParameters;

import java.util.ArrayList;

/**
 * Created by James Euesden on 09/03/2016.
 */
public class GraphDataBuilder {

    public GcResultViewData getGcChartData(GcResult result, UserParameters params){
        GcResultViewData gcResultViewData = new GcResultViewData();
        gcResultViewData.gcPercentages =  result.getGCContentPercentages();

        int[] gcWindowNumbers = new int[gcResultViewData.gcPercentages.size()];
        double[] gcMeanForAllWindows = new double[gcResultViewData.gcPercentages.size()];
        for(int i=0; i < gcResultViewData.gcPercentages.size(); i++){
            gcWindowNumbers[i] = i+1;
            gcMeanForAllWindows[i] = result.mean();
        }
        gcResultViewData.gcWindows = gcWindowNumbers;
        gcResultViewData.gcAverage = result.mean();
        gcResultViewData.gcBarColours = assignBarColours(gcResultViewData.gcPercentages,
                gcResultViewData.gcAverage, params.getAwayFromAverageThreshold());
        gcResultViewData.gcMeanForAllWindows = gcMeanForAllWindows;

        return gcResultViewData;
    }

    private ArrayList<String> assignBarColours(ArrayList<Double> gcContentPercentages, double mean, double awayFromAverageThreshold){
        ArrayList<String> barColours = new ArrayList<String>();
        for(Double currentWindowPercentage : gcContentPercentages){
            if(currentWindowPercentage > (mean + awayFromAverageThreshold) ||
                    currentWindowPercentage < (mean - awayFromAverageThreshold )){
                barColours.add("rgba(222,45,38,0.8)");
            } else {
                barColours.add("rgba(204,204,204,1)");
            }
        }
        return barColours;
    }

}
