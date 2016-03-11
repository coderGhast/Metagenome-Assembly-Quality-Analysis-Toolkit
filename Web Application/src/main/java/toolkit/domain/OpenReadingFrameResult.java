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
}
