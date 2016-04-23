package toolkit.utilities;

import java.util.ArrayList;

/**
 * Created by James Euesden on 13-Mar-16.
 *
 * Calculates the standard deviation of a provided list and the mean of said list.
 */
public class StandardDeviationCalculator {

    /**
     * Returns the standard deviation of a list and the mean of that list.
     * Understanding of how to calculate standard deviation from http://www.mathsisfun.com/data/standard-deviation.html
     * @param gcContentPercentages The list itself.
     * @param mean The mean of the list.
     * @return The resulting standard deviation of the provided list and its mean.
     */
    public static double calculateStandardDeviation(ArrayList<Double> gcContentPercentages, double mean){
        double runningTotal = 0.0;
        for (Double currentPercentage : gcContentPercentages
                ) {
            runningTotal += (currentPercentage - mean) * (currentPercentage - mean);
        }
        return Math.sqrt(runningTotal / gcContentPercentages.size());
    }
}
