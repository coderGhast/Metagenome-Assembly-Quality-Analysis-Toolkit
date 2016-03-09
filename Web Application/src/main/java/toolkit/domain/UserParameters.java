package toolkit.domain;

/**
 * Created by James Euesden on 09/03/2016.
 */
public class UserParameters {

    private double awayFromAverageThreshold = 0;
    private int gcWindowSize = 0;
    private String fileName = "";

    public double getAwayFromAverageThreshold() {
        return awayFromAverageThreshold;
    }

    public void setAwayFromAverageThreshold(double awayFromAverageThreshold) {
        this.awayFromAverageThreshold = awayFromAverageThreshold;
    }

    public int getGcWindowSize() {
        return gcWindowSize;
    }

    public void setGcWindowSize(int gcWindowSize) {
        this.gcWindowSize = gcWindowSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
