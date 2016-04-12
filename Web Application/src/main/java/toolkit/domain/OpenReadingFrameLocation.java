package toolkit.domain;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class OpenReadingFrameLocation {
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
        _orfLength = (_orfStopIndex + 1) - _orfStartIndex;
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