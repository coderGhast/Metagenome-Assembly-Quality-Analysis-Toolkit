package toolkit.utilities;

import toolkit.domain.*;

import java.util.ArrayList;

/**
 * Created by James Euesden on 09/03/2016.
 *
 * Helps build the GC content graph for the View, and make the data accessible in the View with Thymeleaf.
 */
public class GraphDataBuilder {

    /**
     * Returns the data to be used in the View for a GC Content inspection, including some pre-formatted colours
     * for the bar chart, based on the threshold of GC Content percentage. Makes it easier for the View to handle the
     * data.
     * @param result The result of the GC Content inspection.
     * @param awayFromMeanThreshold The amount of standard deviations away from the mean of the GC Content percentage
     *                              that the user has set to be enough to warrant noting.
     * @return The data formatted into an accessible and easily consumable way by the View.
     */
    public static GcResultViewData getGcChartData(GcResult result, double awayFromMeanThreshold){
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
                gcResultViewData.gcAverage, awayFromMeanThreshold);
        gcResultViewData.gcMeanForAllWindows = gcMeanForAllWindows;

        return gcResultViewData;
    }

    // Set the desired bar colours for the GC Content % chart based on if the content % is above or below the set threshold

    /**
     * Creates an array for the colours to be used in the GC Content percentage bar chart for those bars within and out
     * of the threshold of the mean set by the user.
     * @param gcContentPercentages The percentage of the GC content for each window.
     * @param mean The mean of the GC Content windows.
     * @param awayFromMeanThreshold The threshold set by the user, in standard deviations from the mean.
     * @return A list containing the matching colours for each window/bar.
     */
    private static ArrayList<String> assignBarColours(ArrayList<Double> gcContentPercentages, double mean, double awayFromMeanThreshold){
        double threshold = StandardDeviationCalculator.calculateStandardDeviation(gcContentPercentages, mean);
        // Multiply the threshold by the amount of times the user wants the standard deviation away from the mean.
        threshold = threshold * awayFromMeanThreshold;
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
