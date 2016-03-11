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
    GcResult sut;
    ArrayList<GcWindow> testWindows;

    @Before
    public void setup(){
        sut = new GcResult(4);

        ArrayList<GcWindow> testWindows = new ArrayList<>();
        testWindows.add(new GcWindow("GATC"));
        testWindows.add(new GcWindow("CTCA"));
        testWindows.add(new GcWindow("TTTA"));
        sut.setGcWindows(testWindows);
    }

    @Test
    public void shouldBeAbleToGetExpectedTotalGCount(){
        assertEquals("Total G Count should be 1", sut.getTotalGCount(), 1);
    }

    @Test
    public void shouldBeAbleToGetExpectedTotalCCount(){
        assertEquals("Total C Count should be 3", sut.getTotalCCount(), 3);
    }

    @Test
    public void shouldBeAbleToGetExpectedTotalCount(){
        assertEquals("Total Count should be 12", sut.getTotalCount(), 12);
    }

    @Test
    public void shouldBeAbleToGetWindowExpectedWindowSize(){
        assertEquals("Window size should be 4", sut.getWindowSize(), 4);
    }

    @Test
    public void shouldReturnExpectedGcContentPercentages(){
        ArrayList<Double> expectedPercentages = new ArrayList<>();
        expectedPercentages.add(50.0);
        expectedPercentages.add(50.0);
        expectedPercentages.add(0.0);
        ArrayList<Double> actualMeanValues = sut.getGCContentPercentages();
        for(int i=0; i<actualMeanValues.size(); i++){
            assertEquals("Window percentages should match", expectedPercentages.get(i), actualMeanValues.get(i));
        }
    }

    @Test
    public void shouldReturnExpectedMeanValue(){
        testWindows = new ArrayList<>();
        testWindows.add(new GcWindow("GATC"));
        sut.setGcWindows(testWindows);
        assertEquals(50, sut.mean(), 0);
    }

    @Test
    public void shouldReturnExpectedMeanValueWhenThereIsZeroPercent(){
        testWindows = new ArrayList<>();
        testWindows.add(new GcWindow("ATTA"));
        sut.setGcWindows(testWindows);
        assertEquals(0, sut.mean(), 0);
    }
}

