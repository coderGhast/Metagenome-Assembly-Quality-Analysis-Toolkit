package toolkit.unit.utilities;

import org.junit.Test;
import org.mockito.Mock;
import toolkit.domain.GcResult;
import toolkit.domain.GcResultViewData;
import toolkit.utilities.GraphDataBuilder;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by James Euesden on 23/04/2016.
 */
public class GraphDataBuilderTests {
    @Test
    public void getGcChartDataShouldReturnExpectedResults(){
        ArrayList<Double> testPercentages = new ArrayList<>();
        testPercentages.add(10.0);
        testPercentages.add(10.0);
        testPercentages.add(12.0);
        testPercentages.add(10.0);
        ArrayList<String> expectedColours = new ArrayList<>();
        expectedColours.add("rgba(204,204,204,1)");
        expectedColours.add("rgba(204,204,204,1)");
        expectedColours.add("rgba(222,45,38,0.8)");
        expectedColours.add("rgba(204,204,204,1)");

        GcResult mockResult = mock(GcResult.class);
        when(mockResult.getGCContentPercentages()).thenReturn(testPercentages);
        when(mockResult.mean()).thenReturn(10.5);

        GcResultViewData actual = GraphDataBuilder.getGcChartData(mockResult, 1);
        assertEquals(expectedColours, actual.gcBarColours);
    }
}
