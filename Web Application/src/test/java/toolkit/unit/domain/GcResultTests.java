package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.GcResult;
import toolkit.domain.GcWindow;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 2/15/2016.
 */
public class GcResultTests {
    GcResult _sut;
    ArrayList<GcWindow> testWindows;

    @Before
    public void setup(){
        int windowSize = 4;
        _sut = new GcResult(windowSize);
    }

    @Test
    public void shouldBeAbleToGetWindowExpectedWindowSize(){
        assertEquals("Window size should be 4", _sut.getWindowSize(), 4);
    }

    @Test
    public void shouldReturnExpectedGcContentPercentages(){
        ArrayList<Double> expectedPercentages = new ArrayList<>();
        expectedPercentages.add(50.0);
        expectedPercentages.add(50.0);
        expectedPercentages.add(0.0);
        _sut.setGcContentPercentages(expectedPercentages);
        ArrayList<Double> actualMeanValues = _sut.getGCContentPercentages();
        for(int i=0; i<actualMeanValues.size(); i++){
            assertEquals("Window percentages should match", expectedPercentages.get(i), actualMeanValues.get(i));
        }
    }

    @Test
    public void shouldReturnExpectedMeanValue(){
        ArrayList<Double> testPercentages = new ArrayList<>();
        testPercentages.add(11.0);
        testPercentages.add(11.0);
        testPercentages.add(12.0);
        testPercentages.add(11.0);
        _sut.setGcContentPercentages(testPercentages);
        assertEquals(11.25, _sut.mean(), 0);
    }
}

