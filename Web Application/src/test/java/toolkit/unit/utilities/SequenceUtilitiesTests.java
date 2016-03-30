package toolkit.unit.utilities;

import org.junit.Test;
import toolkit.utilities.SequenceUtilities;

import static org.junit.Assert.assertEquals;

/**
 * Created by James Euesden on 30/03/2016.
 */
public class SequenceUtilitiesTests {

    @Test
    public void GetReverseSequenceShouldReturnExpectedSequence(){
        String result = SequenceUtilities.getReverseSequence("ATGC");
        assertEquals("GCAT", result);
    }
}
