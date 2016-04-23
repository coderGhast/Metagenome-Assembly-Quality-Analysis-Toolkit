package toolkit.integration.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import toolkit.domain.*;

import java.io.FileNotFoundException;
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
    public void readSequenceInputShouldReturnListOfContiguousReads(){
        UserParameters testParameters = new UserParameters();
        testParameters.setUserContent(_testContent);
        testParameters.setContigLengthThreshold(0);
        ContigResult result = _sut.readSequenceInput(testParameters);
        ArrayList<ContiguousRead> reads = result.getContigList();
        assertEquals(0, result.getDiscardedContigCount());
        assertEquals(3, reads.size());
        assertEquals(">test_0", reads.get(0).getContigInformation());
        assertEquals(">test_1", reads.get(1).getContigInformation());
        assertEquals(">test_2", reads.get(2).getContigInformation());
    }

    @Test
    public void readUserInputShouldReturnListOfContiguousReads(){
        ContigResult result = _sut.readUserInput(_testContent, 0);
        ArrayList<ContiguousRead> reads = result.getContigList();
        assertEquals(0, result.getDiscardedContigCount());
        assertEquals(3, reads.size());
        assertEquals(">test_0", reads.get(0).getContigInformation());
        assertEquals(">test_1", reads.get(1).getContigInformation());
        assertEquals(">test_2", reads.get(2).getContigInformation());
    }

    @Test
    public void readUserContentShouldIgnoreContigsUnderLengthThreshold(){
        ContigResult result = _sut.readUserInput(_testContent, 40);
        ArrayList<ContiguousRead> reads = result.getContigList();
        assertEquals(2, result.getDiscardedContigCount());
        assertEquals(1, reads.size());
        assertEquals(">test_2", reads.get(0).getContigInformation());
    }

    @Test
    public void readFileShouldReturnListOfContiguousReads(){
        ContigResult result = _sut.readFile(_testFileLocation, 0);
        ArrayList<ContiguousRead> reads = result.getContigList();
        assertEquals(0, result.getDiscardedContigCount());
        assertEquals(3, reads.size());
        assertEquals(">test_0", reads.get(0).getContigInformation());
        assertEquals(">test_1", reads.get(1).getContigInformation());
        assertEquals(">test_2", reads.get(2).getContigInformation());
    }

    @Test
    public void readFileShouldIgnoreContigsUnderLengthThreshold(){
        ContigResult result = _sut.readFile(_testFileLocation, 40);
        ArrayList<ContiguousRead> reads = result.getContigList();
        assertEquals(2, result.getDiscardedContigCount());
        assertEquals(1, reads.size());
        assertEquals(">test_2", reads.get(0).getContigInformation());
    }

    @Test
    public void readFileShouldThrowExceptionWhenFileMissing() {
        try{
            _sut.readFile("doesntexist.fa", 0);
        } catch(Exception e){
            assertEquals(e.getMessage(), "doesntexist.fa (The system cannot find the file specified)");
        }
    }
}


