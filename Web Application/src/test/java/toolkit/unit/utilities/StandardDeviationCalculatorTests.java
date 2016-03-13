package toolkit.unit.utilities;

import org.junit.Before;
import org.junit.Test;
import toolkit.utilities.StandardDeviationCalculator;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by James Euesden on 13-Mar-16.
 */
public class StandardDeviationCalculatorTests {

    @Test
    public void calculateStandardDeviationShouldReturnExpectedResult(){
        ArrayList<Double> values = new ArrayList<>();
        values.add(10.0);
        values.add(8.0);
        values.add(13.2);
        values.add(6.7);
        values.add(11.3);

        assertEquals(2.310497781864332, StandardDeviationCalculator.calculateStandardDeviation(values, mean(values)));
    }

    private double mean(ArrayList<Double> values){
        double total = 0;
        for (Double gcContentPercentage: values) {
            total += gcContentPercentage;
        }
        return total / values.size();
    }

}
