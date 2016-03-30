package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.OpenReadingFrameLocation;
import toolkit.domain.OpenReadingFrameResult;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class OpenReadingFrameResultTests {
    private OpenReadingFrameResult _sut;

    @Before
    public void setup(){
        _sut = new OpenReadingFrameResult();
    }

    @Test
    public void PotentialOrfLocationsListShouldBeEmptyWhenNonAdded(){
        assertEquals(0, _sut.getPotentialOrfLocations().size());
    }

    @Test
    public void PotentialOrfLocationsListShouldContainAddedOrfLocations(){
        _sut.addPotentialOrfLocationToResult(new OpenReadingFrameLocation("ATGCCCCCCCTAG", 0, 12, "+1"));
        assertEquals(1, _sut.getPotentialOrfLocations().size());
        assertEquals("ATGCCCCCCCTAG", _sut.getPotentialOrfLocations().get(0).getOrfCharacters());
    }

    @Test
    public void RemoveLowerThanThresholdOrfLocationsShouldNotRemoveAnyWithNoThreshold(){
        _sut.addPotentialOrfLocationToResult(new OpenReadingFrameLocation("ATGCCCCCCCTAG", 0, 12, "+1"));
        _sut.addPotentialOrfLocationToResult(new OpenReadingFrameLocation("ATGCCCCCCCCCCCCCCCCCCCCTAG", 0, 26, "+1"));
        _sut.removeLowerThanThresholdOrfLocations(0);
        assertEquals(2, _sut.getPotentialOrfLocations().size());
    }

    @Test
    public void RemoveLowerThanThresholdOrfLocationsShouldRemoveAnyLocationsLowerThanThreshold(){
        _sut.addPotentialOrfLocationToResult(new OpenReadingFrameLocation("ATGCCCCCCCTAG", 0, 12, "+1"));
        _sut.addPotentialOrfLocationToResult(new OpenReadingFrameLocation("ATGCCCCCCCCCCCCCCCCCCCCTAG", 0, 26, "+1"));
        _sut.removeLowerThanThresholdOrfLocations(20);
        assertEquals(1, _sut.getPotentialOrfLocations().size());
    }
}
