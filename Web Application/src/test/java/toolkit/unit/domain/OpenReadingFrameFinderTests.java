package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.OpenReadingFrameFinder;
import toolkit.domain.OpenReadingFrameResult;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class OpenReadingFrameFinderTests {

    private OpenReadingFrameFinder _sut;

    @Before
    public void setup(){
        _sut = new OpenReadingFrameFinder();
    }

    @Test
    public void findPotentialOrfLocationsShouldFindNoLocationWhenThereIsNotAnOrf(){
        OpenReadingFrameResult result = _sut.findPotentialOrfLocations("AGGTTAGAGTAA");
        assertEquals(0, result.getPotentialOrfLocations().size());
    }

    @Test
    public void findPotentialOrfLocationShouldNotFindAnOrfWhenStartCodonButNoStopCodon(){
        OpenReadingFrameResult result = _sut.findPotentialOrfLocations("ATGAAGAGTCA");
        assertEquals(0, result.getPotentialOrfLocations().size());
    }

    @Test
    public void findPotentialOrfLocationShouldFindAnOrfWhenStartAndStopCodon(){
        OpenReadingFrameResult result = _sut.findPotentialOrfLocations("ATGAAGAGTAA");
        assertEquals(1, result.getPotentialOrfLocations().size());
        assertEquals("ATGAAGAGTAA", result.getPotentialOrfLocations().get(0).getOrfCharacters());
    }
}
