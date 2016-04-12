package toolkit.domain;

/**
 * Created by James Euesden on 2/22/2016.
 */
public class ContiguousRead {
    private String _contigContext;
    private String _contigInformation;
    private int _contigLength;


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
}
