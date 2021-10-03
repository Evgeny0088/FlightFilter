package tests;

import com.gridnine.testing.utils.UtilFunctions;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FlightFilterHelpMethodsTest {

    @Parameterized.Parameter(0)
    public LocalDateTime departure;
    @Parameterized.Parameter(1)
    public LocalDateTime arrival;
    @Parameterized.Parameter(2)
    public int hours_expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return FlightsRepoTest.SegmentTimeCountDataTest;
    }

    @Test
    public void SegmentTimeCountTest() {
        int checked = UtilFunctions.SegmentTimeCount(departure, arrival);
        System.out.print("time count results: ");
        System.out.println(checked);
        Assertions.assertEquals(hours_expected, checked);
    }
}
