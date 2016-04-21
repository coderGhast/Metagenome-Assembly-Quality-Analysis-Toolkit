package toolkit.domain;


/**
 * Created by James Euesden on 07/04/2016.
 */
public class QualitySummary {
    private GcResult _gcContentResults;
    private OpenReadingFrameResult _orfResults;
    private ContiguousRead _contigInfo;

    public QualitySummary(){
        _contigInfo = new ContiguousRead();
    }

    public void setContiguousRead(ContiguousRead contig){ _contigInfo = contig; }

    public ContiguousRead getContigInfo(){ return _contigInfo; }

    public void addGcResult(GcResult resultToAdd){
        _gcContentResults = resultToAdd;
    }

    public GcResult getGcResults(){
        return _gcContentResults;
    }

    public void addOrfResult(OpenReadingFrameResult resultToAdd){
        _orfResults= resultToAdd;
    }

    public OpenReadingFrameResult getOrfResults(){
        return _orfResults;
    }
}
