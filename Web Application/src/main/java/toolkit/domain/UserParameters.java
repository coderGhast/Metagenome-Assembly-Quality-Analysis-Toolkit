package toolkit.domain;

/**
 * Created by James Euesden on 09/03/2016.
 */
public class UserParameters {
    private String _userContent = "";
    private String _fileName = "";
    private int _contigLengthThreshold = 1000;

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

    public String getFileName() {
        return _fileName;
    }

    public void setFileName(String fileName) {
        this._fileName = fileName;
    }
}
