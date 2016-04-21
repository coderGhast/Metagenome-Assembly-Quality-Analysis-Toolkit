package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 18/04/2016.
 *
 * Represents the results of user input, holding a list of all the ContiguousReads that were in the input that
 * they sent to the application that were valid under the length threshold. Also holds the number of contiguous
 * reads that were discarded due to the length threshold
 */
public class ContigResult {
    private ArrayList<ContiguousRead> _contigList;
    private int _discardedContigCount = 0;

    /**
     * Initializes the object and ArrayList.
     */
    public ContigResult(){
        _contigList = new ArrayList();
    }

    /**
     * Sets the list of ContiguousReads. The object only accepts a full list of contigs, not just individual contigs.
     * @param contigList A full list of ContiguousReads.
     */
    public void setContigList(ArrayList<ContiguousRead> contigList){
        this._contigList = contigList;
    }

    /**
     * Returns the list of ContiguousReads.
     * @return The list of ContiguousReads.
     */
    public ArrayList<ContiguousRead> getContigList(){
        return this._contigList;
    }

    /**
     * Sets the number of contiguous reads that were discarded during the creation of the result of the user input.
     * @param discardCount How many contiguous reads were discarded.
     */
    public void setDiscardedContigCount(int discardCount){
        this._discardedContigCount = discardCount;
    }

    /**
     * Returns the number of contiguous reads that were discarded.
     * @return The number of contiguous reads that were discarded.
     */
    public int getDiscardedContigCount() { return this._discardedContigCount; }
}
