package com.metagenomequalitytoolkit.mmp.tests.unit;

import com.metagenomequalitytoolkit.mmp.GcResult;
import com.metagenomequalitytoolkit.mmp.GcWindow;
import org.junit.Before;
import org.junit.Test;

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
        sut = new GcResult(25);

        ArrayList<GcWindow> testWindows = new ArrayList<>();
        testWindows.add(new GcWindow("GATC"));
        testWindows.add(new GcWindow("CTCA"));
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
        assertEquals("Total Count should be 8", sut.getTotalCount(), 8);
    }

    @Test
    public void shouldBeAbleToGetWindowExpectedWindowSize(){
        assertEquals("Window size should be 25", sut.getWindowSize(), 25);
    }
}
