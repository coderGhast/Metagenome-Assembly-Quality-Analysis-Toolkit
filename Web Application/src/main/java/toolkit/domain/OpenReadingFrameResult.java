package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/22/2016.
 */
public class OpenReadingFrameResult {
    private ArrayList<OpenReadingFrameLocation> _potentialOrfLocations;

    public OpenReadingFrameResult(){
        _potentialOrfLocations = new ArrayList<>();
    }


    public void addPotentialOrfLocationToResult(OpenReadingFrameLocation potentialOrf){
        _potentialOrfLocations.add(potentialOrf);
    }

    public ArrayList<OpenReadingFrameLocation> getPotentialOrfLocations(){
        return _potentialOrfLocations;
    }

    public OpenReadingFrameLocation getLongestOrf(){
        if(_potentialOrfLocations.size() > 0){
            OpenReadingFrameLocation longest = _potentialOrfLocations.get(0);
            for (OpenReadingFrameLocation potentialOrfResult: _potentialOrfLocations) {
                if(potentialOrfResult.orfLength() > longest.orfLength()){
                    longest = potentialOrfResult;
                }
            }
            return longest;
        } else {
            return null;
        }
    }
}
