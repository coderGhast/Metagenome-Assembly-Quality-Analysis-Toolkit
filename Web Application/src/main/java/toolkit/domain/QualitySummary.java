package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 07/04/2016.
 */
public class QualitySummary {
    private ArrayList<GcResult> _gcContentResults;
    private ArrayList<OpenReadingFrameResult> _orfResults;
    private ContiguousRead _contigInfo;

    public QualitySummary(){
        _contigInfo = new ContiguousRead();
        _gcContentResults = new ArrayList<>();
        _orfResults = new ArrayList<>();
    }

    public void setContiguousRead(ContiguousRead contig){ _contigInfo = contig; }

    public ContiguousRead getContigInfo(){ return _contigInfo; }

    public void addGcResult(GcResult resultToAdd){
        _gcContentResults.add(resultToAdd);
    }

    public ArrayList<GcResult> getGcResults(){
        return _gcContentResults;
    }

    public void addOrfResult(OpenReadingFrameResult resultToAdd){
        _orfResults.add(resultToAdd);
    }

    public ArrayList<OpenReadingFrameResult> getOrfResults(){
        return _orfResults;
    }
}
