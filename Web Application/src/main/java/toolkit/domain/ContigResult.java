package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 18/04/2016.
 */
public class ContigResult {
    private ArrayList<ContiguousRead> _contigList;
    private int _discardedContigCount = 0;

    public ContigResult(){
        _contigList = new ArrayList<ContiguousRead>();
    }

    public void setContigList(ArrayList<ContiguousRead> contigList){
        this._contigList = contigList;
    }

    public ArrayList<ContiguousRead> getContigList(){
        return this._contigList;
    }

    public void setDiscardedContigCount(int discardCount){
        this._discardedContigCount = discardCount;
    }

    public int getDiscardedContigCount() { return this._discardedContigCount; }
}
