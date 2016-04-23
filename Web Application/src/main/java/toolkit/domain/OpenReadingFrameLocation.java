package toolkit.domain;

/**
 * Created by James Euesden on 11-Mar-16.
 *
 * Represents an Open Reading Frame Location within a contiguous read.
 */
public class OpenReadingFrameLocation implements Comparable<OpenReadingFrameLocation>{
    private String _orfCharacters;
    private int _orfStartIndex;
    private int _orfStopIndex;
    private int _frameIndicator;
    private int _orfLength;

    /**
     * Initialises the object. It is created with the properties it will have for its life time, since these will
     * never change. When setting the length of the ORF Location, it is calculated from the character difference between
     * the start and stop index, and adjusted depending on if the frame it is located in is forward or backwards.
     * @param orfCharacters The characters within the ORF Location.
     * @param orfStartIndex The character index of the start of the ORF Location.
     * @param orfStopIndex The character index of the end of the ORF Location.
     * @param frameIndicator The frame that the ORF Location belongs to (minus 1)
     */
    public OpenReadingFrameLocation(String orfCharacters, int orfStartIndex, int orfStopIndex, int frameIndicator){
        _orfCharacters = orfCharacters;
        _orfStartIndex = orfStartIndex;
        _orfStopIndex = orfStopIndex;
        _frameIndicator = frameIndicator;
        if(frameIndicator >= 3){
            _orfLength = _orfStartIndex - _orfStopIndex;
        } else {
            _orfLength = _orfStopIndex - _orfStartIndex;
        }
    }

    /**
     * Allows ORF Locations to be compared to one another by length, enabling the application to be able to sort
     * the list by the length.
     * @param otherOrfLoc The OpenReadingFrameLocation to compare against.
     * @return The result of the comparison.
     */
    @Override
    public int compareTo(OpenReadingFrameLocation otherOrfLoc) {
        if(otherOrfLoc.getOrfLength() > this.getOrfLength()){
            return 1;
        } else if (otherOrfLoc.getOrfLength() < this.getOrfLength()){
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Return the characters in the ORF Location.
     * @return The characters in the ORF Location.
     */
    public String getOrfCharacters() {
        return _orfCharacters;
    }

    /**
     * Returns the length/number of characters in the ORF Location.
     * @return The length/number of characters in the ORF Location.
     */
    public int getOrfLength() { return _orfLength; }

    /**
     * Returns the last index of the ORF Location.
     * @return The last index of the ORF Location.
     */
    public int getOrfStopIndex() {
        return _orfStopIndex;
    }

    /**
     * Returns the first index of the ORF Location.
     * @return The first index of the ORF Location.
     */
    public int getOrfStartIndex() {
        return _orfStartIndex;
    }

    /**
     * Returns the frame number, minus 1.
     * @return The frame number, minus 1.
     */
    public int getFrameIndicator(){ return _frameIndicator; }

    /**
     * Checks to see if this ORF Location is the same as the passed Object.
     * @param obj The object to check against.
     * @return The result of the equality check.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!OpenReadingFrameLocation.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final OpenReadingFrameLocation other = (OpenReadingFrameLocation) obj;
        if(!this._orfCharacters.equalsIgnoreCase(other._orfCharacters)){
            return false;
        }

        if (this._orfStartIndex != other._orfStartIndex) {
            return false;
        }
        if (this._orfStopIndex != other._orfStopIndex) {
            return false;
        }
        return true;
    }
}
