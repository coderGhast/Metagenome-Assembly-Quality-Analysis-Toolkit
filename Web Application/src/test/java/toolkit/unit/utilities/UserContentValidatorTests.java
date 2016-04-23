package toolkit.unit.utilities;

import org.junit.Before;
import org.junit.Test;
import toolkit.utilities.UserContentValidator;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by James Euesden on 23/04/2016.
 */
public class UserContentValidatorTests {
    private UserContentValidator _sut;

    @Before
    public void setup(){
        _sut = new UserContentValidator();
    }

    @Test
    public void validateUserContentShouldReturnTrueWhenInputIsValid(){
        boolean result = _sut.validateUserContent(">Something\nATGATAGTAGT");
        assertEquals(true, result);
    }

    @Test
    public void validateUserContentShouldReturnFalseWhenInputIsInvalid(){
        boolean result = _sut.validateUserContent("Notactuallycorrect data");
        assertEquals(false, result);
    }

    @Test
    public void validateUserContentShouldReturnFalseWhenInputIsEmpty(){
        boolean result = _sut.validateUserContent("");
        assertEquals(false, result);
    }

    @Test
    public void validateUserContentShouldReturnFalseWhenInputIsNull(){
        boolean result = _sut.validateUserContent(null);
        assertEquals(false, result);
    }
}
