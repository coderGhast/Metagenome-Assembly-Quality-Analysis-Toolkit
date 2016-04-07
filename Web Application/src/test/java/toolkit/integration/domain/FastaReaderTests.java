package toolkit.integration.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.FastaReader;
import toolkit.domain.GcResult;
import toolkit.domain.QualitySummary;
import toolkit.domain.UserParameters;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 11-Mar-16.
 */
public class FastaReaderTests {
    private FastaReader _sut;

    @Before
    public void setup(){
        _sut = new FastaReader();
    }

    @Test
    public void readFileShouldReturnExpectedGcResultWithTestFile(){
        UserParameters params = new UserParameters();
        params.setFileName("./src/test/resources/static/test.fa");
        params.setContigLengthThreshold(101);
        params.setAwayFromAverageThreshold(1);
        params.setGcWindowSize(4);
        params.setOrfLengthThreshold(100);

        QualitySummary result = _sut.readFile(params);
        GcResult gcResult = result.getGcResults().get(0);
        assertEquals(gcResult.getWindowSize(), 4);
        assertEquals(gcResult.getTotalCount(), 30);
        assertEquals(gcResult.getTotalCCount(), 4);
        assertEquals(gcResult.getTotalGCount(), 10);
    }
}
