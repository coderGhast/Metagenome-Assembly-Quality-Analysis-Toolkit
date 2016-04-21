package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/15/2016.
 *
 * Counts the number of 'G' and 'C' characters in a contiguous read in the window size set by the user, and calculates
 * the percentage of the number of 'G's and 'C's in the window.
 */
public class GcContentCounter {
    /**
     * Splits the contiguous read into windows and then calls to count the GC content percentage for those windows and
     * returns the results of each window together as a GcResult.
     * @param contiguousRead The contiguous read characters.
     * @param windowSize The size of the windows to be considered when calculating the GC content percentage.
     * @return A GcResult containing the results of counting the GC content percentage of each window of the contiguous
     * read.
     */
    public static GcResult countGcContent(String contiguousRead, int windowSize){
        GcResult gcResult = new GcResult(windowSize);
        ArrayList<GcWindow> gcWindows = splitIntoWindows(contiguousRead, windowSize);
        gcResult.setGcContentPercentages(calculateGcContentPercentages(gcWindows));

        return gcResult;
    }

    /**
     * Splits the contiguous read into the individual windows to have the GC content counted and percentage made.
     * @param contiguousRead The contiguous read characters.
     * @param windowSize The size of the substrings (windows) that the contiguous read should be cut into.
     * @return An ArrayList of GcWindows, each containing their window sized share of the contiguous read characters.
     */
    private static ArrayList<GcWindow> splitIntoWindows(String contiguousRead, int windowSize){
        ArrayList<GcWindow> windowedAssembly = new ArrayList<>();
        for(int i=0; i < (contiguousRead.length()); i += windowSize){
                StringBuilder stringBuilder = new StringBuilder();
                if(i + windowSize < contiguousRead.length()){
                    stringBuilder.append(contiguousRead.substring(i, i + windowSize));
                } else {
                    stringBuilder.append(contiguousRead.substring(i));
                }
                windowedAssembly.add(new GcWindow(stringBuilder.toString()));
        }
        return windowedAssembly;
    }

    /**
     * Calculates the GC content percentages based on the contiguous read split into windows.
     * @param windows The windows of the contiguous read.
     * @return The percentage list for each window.
     */
    private static ArrayList<Double> calculateGcContentPercentages(ArrayList<GcWindow> windows){
        ArrayList<Double> percentages = new ArrayList<>();

        for (GcWindow gcWindow: windows) {
            int gcContent = gcWindow.getCCount() + gcWindow.getGCount();
            int contentWindowSize = gcWindow.getWindowContentSize();
            if(gcContent > 0) {
                percentages.add(((new Double(gcContent) / new Double(contentWindowSize)) * 100));
            } else {
                percentages.add(new Double(0));
            }
        }

        return percentages;
    }
}
