package toolkit.utilities;

import toolkit.domain.*;

import java.util.ArrayList;

/**
 * Created by James Euesden on 09/03/2016.
 */
public class GraphDataBuilder {

    public static OpenReadingFrameViewData getOrfChartData(OpenReadingFrameResult result){
        OpenReadingFrameViewData orfResultViewData = new OpenReadingFrameViewData();


        return new OpenReadingFrameViewData();
    }

    public static GcResultViewData getGcChartData(GcResult result, UserParameters params){
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

    // Set the desired bar colours for the GC Content % chart based on if the content % is above or below the set threshold
    private static ArrayList<String> assignBarColours(ArrayList<Double> gcContentPercentages, double mean, double awayFromAverageThreshold){
        double threshold = StandardDeviationCalculator.calculateStandardDeviation(gcContentPercentages, mean);
        // Multiply the threshold by the amount of times the user wants the standard deviation away from the mean.
        threshold = threshold * awayFromAverageThreshold;
        ArrayList<String> barColours = new ArrayList<String>();
        for(Double currentWindowPercentage : gcContentPercentages){
            if(currentWindowPercentage > (mean + threshold) ||
                    currentWindowPercentage < (mean - threshold )){
                barColours.add("rgba(222,45,38,0.8)");
            } else {
                barColours.add("rgba(204,204,204,1)");
            }
        }
        return barColours;
    }
}
