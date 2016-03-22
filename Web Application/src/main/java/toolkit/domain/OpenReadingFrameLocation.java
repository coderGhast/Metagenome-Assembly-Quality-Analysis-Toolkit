package toolkit.domain;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class OpenReadingFrameLocation {
    private String _orfCharacters;
    private int _startCodon;
    private int _stopCodon;

    public OpenReadingFrameLocation(String orfCharacters, int startCodon, int stopCodon){
        _orfCharacters = orfCharacters;
        _startCodon = startCodon;
        _stopCodon = stopCodon;
    }

    public String getOrfCharacters() {
        return _orfCharacters;
    }

    public int orfLength(){
        return _stopCodon - _startCodon;
    }

    public int getStopCodon() {
        return _stopCodon;
    }

    public int getStartCodon() {
        return _startCodon;
    }
}
