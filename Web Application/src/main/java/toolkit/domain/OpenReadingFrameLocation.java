package toolkit.domain;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class OpenReadingFrameLocation implements Comparable<OpenReadingFrameLocation>{
    private String _orfCharacters;
    private int _orfStartIndex;
    private int _orfStopIndex;
    private int _frameIndicator;
    private int _orfLength;

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

    public String getOrfCharacters() {
        return _orfCharacters;
    }

    public int getOrfLength() { return _orfLength; }

    public int getOrfStopIndex() {
        return _orfStopIndex;
    }

    public int getOrfStartIndex() {
        return _orfStartIndex;
    }

    public int getFrameIndicator(){ return _frameIndicator; }

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
