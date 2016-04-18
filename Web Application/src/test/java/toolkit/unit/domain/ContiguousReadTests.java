package toolkit.unit.domain;

import org.junit.Before;
import org.junit.Test;
import toolkit.domain.ContiguousRead;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by James Euesden on 18/04/2016.
 */
public class ContiguousReadTests {
    private ContiguousRead _sut;

    @Before
    public void setup(){
        _sut = new ContiguousRead();
    }

    @Test
    public void WhenTheContextOfTheContigIsSetTheNumberOfNShouldBeCalculated(){
        _sut.setContigContext("ATGNNNGNTNGCNAGG");
        assertEquals(6, _sut.getNumberOfN());
    }

    @Test
    public void WhenTheContextOfTheContigIsSetThePercentageOfNShouldBeCalculated(){
        _sut.setContigContext("GGGGGGGGGNNNNNNNNN");
        assertEquals(50.0, _sut.getPercentageOfN());
    }
}
