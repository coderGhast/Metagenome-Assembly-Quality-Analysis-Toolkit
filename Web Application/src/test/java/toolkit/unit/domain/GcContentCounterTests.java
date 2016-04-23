package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.GcContentCounter;
import toolkit.domain.GcResult;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by James Euesden on 23/04/2016.
 */
public class GcContentCounterTests {
    private String _testContig;
    private int _testWindowSize;

    @Before
    public void setup(){
        _testContig = "CAAAAAAAAA" +
                    "GTTTTTTTTT" +
                "GCTTTTTTTT" +
                "GTTTTTTTTT";
        _testWindowSize = 10;
    }

    @Test
    public void countGcContentShouldReturnTheExpectedResults(){
        ArrayList<Double> testPercentages = new ArrayList<>();
        testPercentages.add(10.0);
        testPercentages.add(10.0);
        testPercentages.add(20.0);
        testPercentages.add(10.0);
        GcResult expectedGcResult = mock(GcResult.class);
        when(expectedGcResult.getGCContentPercentages()).thenReturn(testPercentages);
        GcResult actual = GcContentCounter.countGcContent(_testContig, _testWindowSize);
        assertEquals(expectedGcResult.getGCContentPercentages(), actual.getGCContentPercentages());
    }
}
