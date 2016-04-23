package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.ContiguousRead;
import toolkit.domain.GcResult;
import toolkit.domain.OpenReadingFrameResult;
import toolkit.domain.QualitySummary;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by James Euesden on 23/04/2016.
 */
public class QualitySummaryTests {
    private QualitySummary _sut;
    private OpenReadingFrameResult _mockOrfResult;
    private GcResult _mockGcResult;
    private ContiguousRead _mockContigResult;

    @Before
    public void setup(){
        _mockOrfResult = mock(OpenReadingFrameResult.class);
        _mockGcResult = mock(GcResult.class);
        _mockContigResult = mock(ContiguousRead.class);
        _sut = new QualitySummary();
    }

    @Test
    public void theSetOrfResultShouldBeTheReturnedOrfResult(){
        _sut.addOrfResult(_mockOrfResult);
        assertEquals(_mockOrfResult, _sut.getOrfResults());
    }

    @Test
    public void theSetGcResultShouldBeTheReturnedGcResult(){
        _sut.addGcResult(_mockGcResult);
        assertEquals(_mockGcResult, _sut.getGcResults());
    }

    @Test
    public void theSetContiguousReadShouldBeTheReturnedContiguousRead(){
        _sut.setContiguousRead(_mockContigResult);
        assertEquals(_mockContigResult, _sut.getContigInfo());
    }
}
