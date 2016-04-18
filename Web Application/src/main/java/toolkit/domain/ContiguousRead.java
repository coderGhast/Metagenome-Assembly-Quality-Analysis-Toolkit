package toolkit.domain;

import java.math.BigDecimal;

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
    private int _numberOfN = 0;
    private double _percentageOfN = 0;

    public String getContigContext() {
        return _contigContext;
    }

    public void setContigContext(String contigContext) {
        this._contigContext = contigContext;
        this._contigLength = _contigContext.length();
        calculateNumberOfN();
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

    public int getNumberOfN(){ return _numberOfN; }

    public void calculateNumberOfN(){
        for(int i=0; i < _contigContext.length(); i++){
            if(_contigContext.toLowerCase().charAt(i) == 'n'){
                _numberOfN++;
            }
        }
        if(_numberOfN > 0 && _contigLength > 0){
            _percentageOfN = ((double) _numberOfN / (double) _contigLength) * 100;
        }
    }

    public double getPercentageOfN(){
        Double truncatedPercentageOfN = new BigDecimal(_percentageOfN)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        return truncatedPercentageOfN;
    }
}
