package toolkit.unit;

import org.junit.Test;
import toolkit.GcWindow;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 2/15/2016.
 */
public class GcWindowTests {

    GcWindow sut;

    @Test
    public void gContentCountShouldBeAsExpected(){
        String testContent = "GAtgcccTAT";
        sut = new GcWindow(testContent);

        assertEquals("G Content should be 2", sut.getGCount(), 2);
    }

    @Test
    public void cContentCountShouldBeAsExpected(){
        String testContent = "GAtgcccTAT";
        sut = new GcWindow(testContent);

        assertEquals("C Content should be 3", sut.getCCount(), 3);
    }

    @Test
    public void windowSizeShouldBeAsExpected(){
        String testContent = "GAtgcccTAT";
        sut = new GcWindow(testContent);

        assertEquals("Window size should be 10", sut.getWindowContentSize(), 10);
    }
}
