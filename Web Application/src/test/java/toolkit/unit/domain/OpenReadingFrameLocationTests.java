package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.OpenReadingFrameLocation;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class OpenReadingFrameLocationTests {
    private OpenReadingFrameLocation _sut;

    @Before
    public void setup(){
        _sut = new OpenReadingFrameLocation("ATGCCCCCCCTAG", 0, 12, 3);
    }

    @Test
    public void constructorOfOpenReadingFrameLocationShouldSetFields(){
        assertEquals("ATGCCCCCCCTAG", _sut.getOrfCharacters());
        assertEquals(0, _sut.getOrfStartIndex());
        assertEquals(12, _sut.getOrfStopIndex());
    }


}
