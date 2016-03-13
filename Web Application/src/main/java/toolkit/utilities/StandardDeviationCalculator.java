package toolkit.utilities;

import java.util.ArrayList;

/**
 * Created by James Euesden on 13-Mar-16.
 */
public class StandardDeviationCalculator {

    public static double calculateStandardDeviation(ArrayList<Double> gcContentPercentages, double mean){
        double runningTotal = 0.0;
        for (Double currentPercentage : gcContentPercentages
                ) {
            runningTotal += (currentPercentage - mean) * (currentPercentage - mean);
        }
        return Math.sqrt(runningTotal / gcContentPercentages.size());
    }
}
