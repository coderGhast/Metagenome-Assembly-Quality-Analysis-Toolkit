package toolkit.domain;


/**
 * Created by James Euesden on 07/04/2016.
 *
 * Holds the results of each quality inspection result to be provided to the View from the Controller.
 */
public class QualitySummary {
    private GcResult _gcContentResults;
    private OpenReadingFrameResult _orfResults;
    private ContiguousRead _contigInfo;

    /**
     * Creates a new QualitySummary object, and holds a blank contiguous read to begin with.
     */
    public QualitySummary(){
        _contigInfo = new ContiguousRead();
    }

    /**
     * Sets the contiguous read being inspected.
     * @param contig The contiguous read the user wishes to inspect.
     */
    public void setContiguousRead(ContiguousRead contig){ _contigInfo = contig; }

    /**
     * Returns the information about the contiguous read being inspected.
     * @return The information about the contiguous read being inspected.
     */
    public ContiguousRead getContigInfo(){ return _contigInfo; }

    /**
     * Adds the result of a GC Content inspection.
     * @param resultToAdd The result of a GC Content inspection.
     */
    public void addGcResult(GcResult resultToAdd){
        _gcContentResults = resultToAdd;
    }

    /**
     * Returns the known GC Content inspection result.
     * @return The known GC Content inspection result.
     */
    public GcResult getGcResults(){
        return _gcContentResults;
    }

    /**
     * Adds the result of an Open Reading Frame inspection.
     * @param resultToAdd The result of an Open Reading Frame inspection.
     */
    public void addOrfResult(OpenReadingFrameResult resultToAdd){
        _orfResults= resultToAdd;
    }

    /**
     * Returns the result of an Open Reading Frame inspection.
     * @return The result of an Open Reading Frame inspection.
     */
    public OpenReadingFrameResult getOrfResults(){
        return _orfResults;
    }
}
