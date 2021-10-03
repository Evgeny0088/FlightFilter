package tests;

import com.gridnine.testing.FlightFilter.FlightFilter;
import com.gridnine.testing.Flights.Flight;
import com.gridnine.testing.utils.UtilFunctions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterTest {

    private static final List<Flight> testFlights = FlightsRepoTest.createFlights();

    @Test
    void getFilter(){
        FlightFilter f1 = FlightFilter.getFilter();
        FlightFilter f2 = FlightFilter.getFilter();
        assertEquals(f1, f2);
    }

    @Test
    void allFlightsTest(){
        List<Flight> expected = testFlights;
        List<Flight> checked = FlightFilter.getFilter().allFlights(testFlights);
        System.out.println("Expected list:");
        UtilFunctions.FlightsRepresentation(expected,testFlights);
        System.out.println("checked list:");
        UtilFunctions.FlightsRepresentation(checked,testFlights);
        assertEquals(expected,checked);
    }

    @Test
    void passedFlightsTest() {
        List<Flight> expected = new ArrayList<>(testFlights.subList(0,12));
        expected.add(testFlights.get(25));
        List<Flight> checked = FlightFilter.getFilter().passedFlights(testFlights);
        System.out.println("Expected list:");
        UtilFunctions.FlightsRepresentation(expected,testFlights);
        System.out.println("checked list:");
        UtilFunctions.FlightsRepresentation(checked,testFlights);
        assertEquals(expected,checked);
    }

    @Test
    void disorderedFlightsTest() {
        List<Flight> expected = testFlights.subList(12,21);
        List<Flight> checked = FlightFilter.getFilter().disorderedFlights(testFlights);
        System.out.println("Expected list:");
        UtilFunctions.FlightsRepresentation(expected,testFlights);
        System.out.println("checked list:");
        UtilFunctions.FlightsRepresentation(checked,testFlights);
        assertEquals(expected,checked);
    }

    @Test
    void LongestGroundTimeFlightsTest() {
        List<Flight> expected = List.of(testFlights.get(4),testFlights.get(9),testFlights.get(25));
        List<Flight> checked = FlightFilter.getFilter().LongestGroundTimeFlights(testFlights);
        System.out.println("Expected list:");
        UtilFunctions.FlightsRepresentation(expected,testFlights);
        System.out.println("checked list:");
        UtilFunctions.FlightsRepresentation(checked,testFlights);
        assertEquals(expected,checked);
    }

    @Test
    void fastestFlightTest() {
        List<Flight> expected = List.of(testFlights.get(1));
        List<Flight> checked = FlightFilter.getFilter().fastestFlight(testFlights);
        System.out.println("Expected flight:");
        UtilFunctions.FlightsRepresentation(expected,testFlights);
        System.out.println("checked flight:");
        UtilFunctions.FlightsRepresentation(checked,testFlights);
        assertEquals(expected,checked);
    }

    @Test
    void longestFlight() {
        List<Flight> expected = List.of(testFlights.get(9));
        List<Flight> checked = FlightFilter.getFilter().longestFlight(testFlights);
        System.out.println("Expected flight:");
        UtilFunctions.FlightsRepresentation(expected,testFlights);
        System.out.println("checked flight:");
        UtilFunctions.FlightsRepresentation(checked,testFlights);
        assertEquals(expected,checked);
    }
}