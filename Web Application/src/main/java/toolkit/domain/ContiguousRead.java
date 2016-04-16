package toolkit.domain;

/**
 * Created by James Euesden on 2/22/2016.
 */
public class ContiguousRead {
    private String _contigContext;
    private String _contigInformation;
    private int _contigLength;
    private double _awayFromAverageThreshold = 1;
    private int _gcWindowSize = 300;
    private int _orfLengthThreshold = 100;


    public String getContigContext() {
        return _contigContext;
    }

    public void setContigContext(String contigContext) {
        this._contigContext = contigContext;
    }

    public String getContigInformation() {
        return _contigInformation;
    }

    public void setContigInformation(String contigInformation) {
        this._contigInformation = contigInformation;
    }

    public int getContigLength() { return _contigLength; }

    public void setContigLength(int length) { this._contigLength = length; }

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
}
