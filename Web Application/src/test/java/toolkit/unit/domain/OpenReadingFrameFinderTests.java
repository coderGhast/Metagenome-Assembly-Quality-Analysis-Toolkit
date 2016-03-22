package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import toolkit.domain.OpenReadingFrameFinder;
import toolkit.domain.OpenReadingFrameResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 11-Mar-16.
 */
@RunWith(Parameterized.class)
public class OpenReadingFrameFinderTests {

    private OpenReadingFrameFinder _sut;
    private String _testContig;
    private int _expectedNumberOfOrfs;
    private ArrayList<String> _expectedOrfs;

    public OpenReadingFrameFinderTests(String testContig, int expectedNumberOfOrfs, ArrayList<String> expectedOrfs){
        _testContig = testContig;
        _expectedNumberOfOrfs = expectedNumberOfOrfs;
        _expectedOrfs = expectedOrfs;
    }

    @Before
    public void setup(){
        _sut = new OpenReadingFrameFinder();
    }

    @Parameterized.Parameters
    public static Collection parameters(){

        ArrayList<String> testCaseOne = new ArrayList<>();
        testCaseOne.add("ATGAAGAGCTAA");

        ArrayList<String> testCaseTwo = new ArrayList<>();
        testCaseTwo.add("ATGAAGAGCTAA");

        ArrayList<String> testCaseThree = new ArrayList<>();
        testCaseThree.add("ATGAAGAGCTAA");
        testCaseThree.add("ATGCCATAA");

        return Arrays.asList(new Object[][] {
                { "AGGTTAGAGCTAA", 0, new ArrayList<String>() },
                { "ATGAAGAGCTAA", 1, testCaseTwo},
                { "CATGAAGAGCTAA", 1, testCaseOne},
                { "ATGAAGAGCTAAGCGATGCCATAA", 2, testCaseThree}
        });
    }

    @Test
    public void findPotentialOrfLocationsShouldFindExpectedResults() {
        OpenReadingFrameResult result = _sut.findPotentialOrfLocations(_testContig);
        assertEquals(_expectedNumberOfOrfs, result.getPotentialOrfLocations().size());
        if(_expectedNumberOfOrfs > 0){
            for (int i = 0; i < _expectedNumberOfOrfs; i++) {
                String currentExpectedOrf = _expectedOrfs.get(i);
                assertEquals(currentExpectedOrf, result.getPotentialOrfLocations().get(i).getOrfCharacters());
            }
        }
    }
}
