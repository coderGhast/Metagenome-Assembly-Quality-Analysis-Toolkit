package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import toolkit.domain.OpenReadingFrameFinder;
import toolkit.domain.OpenReadingFrameLocation;
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
    private ArrayList<OpenReadingFrameLocation> _expectedOrfs;

    public OpenReadingFrameFinderTests(String testContig, int expectedNumberOfOrfs, ArrayList<OpenReadingFrameLocation> expectedOrfs){
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

        ArrayList<OpenReadingFrameLocation> testCaseZero = new ArrayList<>();
        testCaseZero.add(new OpenReadingFrameLocation("ATGAAGAGCTAA",0,12, 0));

        ArrayList<OpenReadingFrameLocation> testCaseOne = new ArrayList<>();
        testCaseOne.add(new OpenReadingFrameLocation("ATGAAGAGCTAA",0, 12, 0));

        ArrayList<OpenReadingFrameLocation> testCaseTwo = new ArrayList<>();
        testCaseTwo.add(new OpenReadingFrameLocation("ATGAAGAGCTAA", 1, 13, 1));

        ArrayList<OpenReadingFrameLocation> testCaseThree = new ArrayList<>();
        testCaseThree.add(new OpenReadingFrameLocation("ATGAAGAGCTAA",0,12, 0));
        testCaseThree.add(new OpenReadingFrameLocation("ATGCCATAA", 15, 24, 0));

        ArrayList<OpenReadingFrameLocation> testCaseFour = new ArrayList<>();
        testCaseFour.add(new OpenReadingFrameLocation("ATGGCGCCCGTAATGGACGGCTAG", 2, 26, 2));

        ArrayList<OpenReadingFrameLocation> testCaseFive = new ArrayList<>();
        testCaseFive.add(new OpenReadingFrameLocation("ATGGCGATGGCGATGGCGTAATTTTAG", 15, 42, 0));
        testCaseFive.add(new OpenReadingFrameLocation("ATGATAGCGTGA", 45, 57, 0));
        testCaseFive.add(new OpenReadingFrameLocation("ATGATGGCGATCTAGTGACGCTAA", 60, 84, 0));

        ArrayList<OpenReadingFrameLocation> testCaseSix = new ArrayList<>();
        testCaseSix.add(new OpenReadingFrameLocation("ATGGCATGA", 0, 9, 0));
        testCaseSix.add(new OpenReadingFrameLocation("ATGAGGCGCTAA", 5, 17, 2));

        // Reverse frame, so start and stop are 'swapped' in test case
        ArrayList<OpenReadingFrameLocation> testCaseSeven = new ArrayList<>();
        testCaseSeven.add(new OpenReadingFrameLocation("ATGCCCGGGTGA", 12, 0, 3));

        // Expected ORF Locations in upper case for ease of viewing
        return Arrays.asList(new Object[][] {
                // No ORF Location
                { "aggttagagctaatgc", 0, testCaseZero },
                // 1 ORF Location - Frame 1
                { "ATGAAGAGCTAAtcg", 1, testCaseOne},
                // 1 ORF Location - Frame 2
                { "cATGAAGAGCTAAgct", 1, testCaseTwo},
                // 2 ORF Locations - Both Frame 1
                { "ATGAAGAGCTAAgcgATGCCATAAgcg", 2, testCaseThree},
                // 1 ORF Location - Frame 3, including a redundant start codon in the same frame and stop codon in another frame
                { "gcATGGCGCCCGTAATGGACGGCTAGgcg", 1, testCaseFour },
                // 3 ORF Locations - Frame 1, including redundant starts and stop codons in the same frame
                {"taagcgtaggccggtATGGCGATGGCGATGGCGTAATTTTAGgcgATGATAGCGTGAggaatgATGGCGATCTAGTGACGCTAA", 3, testCaseFive},
                // 2 ORF Locations - In Frame 1 and Frame 3
                {"ATGGCATGAGGCGCTAA", 2, testCaseSix},
                // 1 ORF Location - In Frame 4 (reverse contig)
                {"TCACCCGGGCAT", 1, testCaseSeven}
        });
    }

    @Test
    public void findPotentialOrfLocationsShouldFindExpectedResults() {
        OpenReadingFrameResult result = _sut.findPotentialOrfLocations(_testContig);
        assertEquals(_expectedNumberOfOrfs, result.getPotentialOrfLocations().size());

        if(_expectedNumberOfOrfs > 0){
            for (int i = 0; i < _expectedNumberOfOrfs; i++) {
                OpenReadingFrameLocation currentExpectedOrf = _expectedOrfs.get(i);
                OpenReadingFrameLocation actualOrf = result.getPotentialOrfLocations().get(i);
                assertEquals("Expected: " + currentExpectedOrf.getOrfCharacters() + " Start: " + currentExpectedOrf.getOrfStartIndex() +
                        " Ends: " + currentExpectedOrf.getOrfStopIndex() + ", Actual: "
                                + actualOrf.getOrfCharacters() +" Start: " + actualOrf.getOrfStartIndex() +
                                " Ends: " + actualOrf.getOrfStopIndex()
                        ,currentExpectedOrf, actualOrf);
            }
        }
    }
}
