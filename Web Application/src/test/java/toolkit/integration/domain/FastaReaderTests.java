package toolkit.integration.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class FastaReaderTests {
    private FastaReader _sut;
    private String _testContent;
    private String _testFileLocation;

    @Before
    public void setup(){
        _sut = new FastaReader();
        _testFileLocation = "./src/test/resources/static/test.fa";
        _testContent =  ">test_0\n" +
                "CTAGAGAGA\n" +
                "TAGCGATGC\n" +
                "GGCTATAGA\n" +
                "TAG\n" +
                ">test_1\n" +
                "CTAGAGAGA\n" +
                ">test_2\n" +
                "CTAGAGAGA\n" +
                "TAGCGATGC\n" +
                "GGCTATAGA\n" +
                "CTAGAGAGA\n" +
                "TAGCGATGC\n" +
                "GGCTATAGA";
    }

    @Test
    public void readUserInputShouldReturnListOfContiguousReads(){
        ArrayList<ContiguousRead> result = _sut.readUserInput(_testContent, 0);
        assertEquals(3, result.size());
        assertEquals(">test_0", result.get(0).getContigInformation());
        assertEquals(">test_1", result.get(1).getContigInformation());
        assertEquals(">test_2", result.get(2).getContigInformation());
    }

    @Test
    public void readUserContentShouldIgnoreContigsUnderLengthThreshold(){
        ArrayList<ContiguousRead> result = _sut.readUserInput(_testContent, 40);
        assertEquals(1, result.size());
        assertEquals(">test_2", result.get(0).getContigInformation());
    }

    @Test
    public void readFileShouldReturnListOfContiguousReads(){
        ArrayList<ContiguousRead> result = _sut.readFile(_testFileLocation, 0);
        assertEquals(3, result.size());
        assertEquals(">test_0", result.get(0).getContigInformation());
        assertEquals(">test_1", result.get(1).getContigInformation());
        assertEquals(">test_2", result.get(2).getContigInformation());
    }

    @Test
    public void readFileShouldIgnoreContigsUnderLengthThreshold(){
        ArrayList<ContiguousRead> result = _sut.readFile(_testFileLocation, 40);
        assertEquals(1, result.size());
        assertEquals(">test_2", result.get(0).getContigInformation());
    }
}
