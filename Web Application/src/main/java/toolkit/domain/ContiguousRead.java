package toolkit.domain;

import java.math.BigDecimal;

/**
 * Created by James Euesden on 2/22/2016.
 *
 * This class represents a contiguous read from the input user data. It also includes the data for a users requested
 * parameters when conducting an inspection on the contiguous read. Used by thymeleaf in the View to display the
 * contiguous read data.
 * The data for the parameters for the inpsection are kept in this class in order to be carried through by Thymeleaf.
 */
public class ContiguousRead {
    private String _contigContext;
    private String _contigInformation;
    private int _contigLength;
    private double _awayFromMeanThreshold = 1;
    private int _gcWindowSize = 300;
    private int _orfLengthThreshold = 100;
    private int _numberOfN = 0;
    private double _percentageOfN = 0;

    /**
     * Returns the characters of the contiguous read that make up the sequences.
     * @return the characters of the contiguous read that make up the sequences.
     */
    public String getContigContext() {
        return _contigContext;
    }

    /**
     * Sets the characters of the contiguous read, and the length field from the size of the contiguous read.
     * @param contigContext The characters of the contiguous read that make up the sequences.
     */
    public void setContigContext(String contigContext) {
        this._contigContext = contigContext;
        this._contigLength = _contigContext.length();
        calculateNumberOfN();
    }

    /**
     * Returns the information about the contiguous read from the contiguous read header.
     * @return The information about the contiguous read from the contiguous read header.
     */
    public String getContigInformation() {
        return _contigInformation;
    }

    /**
     * Sets the information about the contiguous read from the contiguous read header.
     * @param contigInformation The information about the contiguous read from the contiguous read header.
     */
    public void setContigInformation(String contigInformation) {
        this._contigInformation = contigInformation;
    }

    /**
     * Returns the length of the contiguous read, based on the length of the contigContext upon entry.
     * @return
     */
    public int getContigLength() { return _contigLength; }

    /**
     * Sets the minimum length of an Open Reading Frame Location for it to be considered.
     * @param orfLengthThreshold The minimum length of an Open Reading Frame Location for it to be considered.
     */
    public void setOrfLengthThreshold(int orfLengthThreshold){
        this._orfLengthThreshold = orfLengthThreshold;
    }

    /**
     * Returns the minimum length an Open Reading Frame Location must be from this contiguous read in order to
     * be considered.
     * @return The minimum length of an Open Reading Frame Location for it to be considered.
     */
    public int getOrfLengthThreshold() { return _orfLengthThreshold; }

    /**
     * Returns the value for how far away from the mean a GC Content windows percentage can be to be considered over or
     * under the threshold (by standard deviations).
     * @return The value for how far away from the mean a GC Content windows percentage can be to be considered over or
     * under the threshold (by standard deviations).
     */
    public double getAwayFromMeanThreshold() {
        return _awayFromMeanThreshold;
    }

    /**
     * Sets the value for how far away from the mean a GC Content windows percentage can be to be considered over or
     * under the threshold (by standard deviations).
     * @param awayFromMeanThreshold The value for how far away from the mean a GC Content windows percentage can be to be considered over or
     * under the threshold (by standard deviations).
     */
    public void setAwayFromMeanThreshold(double awayFromMeanThreshold) {
        this._awayFromMeanThreshold = awayFromMeanThreshold;
    }

    /**
     * Returns the size of the GC Windows to be used.
     * @return The size of the GC Windows to be used.
     */
    public int getGcWindowSize() {
        return _gcWindowSize;
    }

    /**
     *  Sets the size of the GC Windows to be used.
     * @param gcWindowSize The size of the GC Windows to be used.
     */
    public void setGcWindowSize(int gcWindowSize) {
        this._gcWindowSize = gcWindowSize;
    }

    /**
     * Returns the number of 'N' characters within the contiguous read context.
     * @return The number of 'N' characters within the contiguous read context.
     */
    public int getNumberOfN(){ return _numberOfN; }

    /**
     * Counts the number of N characters in the contiguous read. This then sets the fields of this class to be
     * retrieved at a later time. This method is separate from the retrieving as counting the number of the
     * N characters in the contiguous read on every request to see the number could take a long time. This time
     * is reduced by just counting and calculating it once and then returning the static number later, as the
     * context of the contiguous read should never change, so nor should this number.
     */
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

    /**
     * Returns the percentage of the contiguous read that is made up of 'N' characters.
     * @return The percentage of the contiguous read that is made up of 'N' characters.
     */
    public double getPercentageOfN(){
        Double truncatedPercentageOfN = new BigDecimal(_percentageOfN)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        return truncatedPercentageOfN;
    }
}
