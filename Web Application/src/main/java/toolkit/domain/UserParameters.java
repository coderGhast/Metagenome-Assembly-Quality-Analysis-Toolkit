package toolkit.domain;

/**
 * Created by James Euesden on 09/03/2016.
 */
public class UserParameters {
    private String _userContent = "";
    private double _awayFromAverageThreshold = 1;
    private int _gcWindowSize = 300;
    private String _fileName = "";
    private int _contigLengthThreshold = 0;
    private int _orfLengthThreshold = 0;

    public String getUserContent(){ return _userContent; }

    public void setUserContent(String userContent){
        this._userContent = userContent;
    }

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
