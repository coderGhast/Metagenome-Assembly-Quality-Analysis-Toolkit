package toolkit.domain;

import java.util.ArrayList;

/**
 * Created by James Euesden on 2/22/2016.
 *
 * This Object contains the results of looking for Open Reading Frames within a contiguous read.
 */
public class OpenReadingFrameResult {
    private ArrayList<OpenReadingFrameLocation> _potentialOrfLocations;

    /**
     * Initializes the object and its components.
     */
    public OpenReadingFrameResult(){
        _potentialOrfLocations = new ArrayList<>();
    }

    /**
     * Adds a single OpenReadingFrameLocation to the list of currently held ORF Locations.
     * @param potentialOrf The ORF Location to add.
     */
    public void addPotentialOrfLocationToResult(OpenReadingFrameLocation potentialOrf){
        _potentialOrfLocations.add(potentialOrf);
    }

    /**
     * Adds an entire list of ORF Locations to the currently held ORF Locations.
     * @param list The list to add to the currently held list of ORF Locations.
     */
    public void addPotentialOrfLocationListToResult(ArrayList<OpenReadingFrameLocation> list){
        for (int i = 0; i < list.size(); i++) {
            this.addPotentialOrfLocationToResult(list.get(i));
        }
    }

    /**
     * Returns all the known potential ORF Locations in this object at the time.
     * @return All the known potential ORF Locations in this object at the time.
     */
    public ArrayList<OpenReadingFrameLocation> getPotentialOrfLocations(){
        return _potentialOrfLocations;
    }

    /**
     * Removes any ORF Locations within the currently held list that are below the provided length threshold.
     * @param orfLengthThreshold The minimum size that an ORF Location can be to be considered.
     */
    public void removeLowerThanThresholdOrfLocations(int orfLengthThreshold){
        ArrayList<OpenReadingFrameLocation> toRemove = new ArrayList<>();
        for (OpenReadingFrameLocation location : _potentialOrfLocations) {
            if(orfLengthThreshold > 0){
                if(location.getOrfLength() < orfLengthThreshold){
                    toRemove.add(location);
                }
            }
        }

        for(OpenReadingFrameLocation location: toRemove){
            _potentialOrfLocations.remove(location);
        }
    }
}
