package toolkit.domain;

/**
 * Created by James Euesden on 09/03/2016.
 */
public class UserParameters {

    private double _awayFromAverageThreshold = 0;
    private int _gcWindowSize = 0;
    private String _fileName = "";
    private int _contigLengthThreshold = 100;
    private int _orfLengthThreshold = 0;

    public int getContigLengthThreshold() {
        return _contigLengthThreshold;
    }

    public void setContigLengthThreshold(int contigLengthThreshold) {
        this._contigLengthThreshold = contigLengthThreshold;
    }

    public void setOrfLengthThreshold(int orfLengthThreshold){
        this._orfLengthThreshold = orfLengthThreshold;
    }

    public int getOrfLengthThreshold() { return _orfLengthThreshold; }

    public double getAwayFromAverageThreshold() {
        return _awayFromAverageThreshold;
    }

    public void setAwayFromAverageThreshold(double awayFromAverageThreshold) {
        this._awayFromAverageThreshold = awayFromAverageThreshold;
    }

    public int getGcWindowSize() {
        return _gcWindowSize;
    }

    public void setGcWindowSize(int gcWindowSize) {
        this._gcWindowSize = gcWindowSize;
    }

    public String getFileName() {
        return _fileName;
    }

    public void setFileName(String fileName) {
        this._fileName = fileName;
    }
}
