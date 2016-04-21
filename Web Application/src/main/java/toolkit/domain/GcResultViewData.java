package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 09/03/2016.
 *
 * A class for holding the results of the GC content calculations for use in the view.
 */
public class GcResultViewData {
    public ArrayList<Double> gcPercentages;
    public int[] gcWindows;
    public double gcAverage;
    public ArrayList<String> gcBarColours;
    public double[] gcMeanForAllWindows;
}
