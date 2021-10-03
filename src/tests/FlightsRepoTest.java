package tests;

import com.gridnine.testing.Flights.Flight;
import com.gridnine.testing.Flights.FlightBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Flight repository where stored all available flights
 */
public interface FlightsRepoTest {
    LocalDateTime flightTime = LocalDateTime.now();

    // Data for filters verification, used in FlightFilterTest.java
    static List<Flight> createFlights() {
        return Arrays.asList(
                // passedFlights test expected section!!
                FlightBuilder.createFlight(flightTime, flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.minusMinutes(5), flightTime.plusHours(2)),
                FlightBuilder.createFlight(flightTime.minusHours(1), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.minusHours(2), flightTime.plusHours(2)),
                FlightBuilder.createFlight(flightTime, flightTime.plusHours(2),
                        flightTime.plusHours(5), flightTime.plusHours(10)),
                FlightBuilder.createFlight(flightTime.minusHours(10), flightTime.plusHours(2),
                        flightTime.plusHours(4), flightTime.plusHours(10)),
                FlightBuilder.createFlight(flightTime.minusMinutes(5), flightTime.plusHours(2)),
                FlightBuilder.createFlight(flightTime.minusHours(1), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime, flightTime.plusHours(2),
                        flightTime.plusHours(4), flightTime.plusHours(10)),
                FlightBuilder.createFlight(flightTime.minusMonths(30), flightTime.plusHours(2),
                        flightTime.plusHours(5), flightTime.plusHours(10)),
                FlightBuilder.createFlight(flightTime.minusHours(10), flightTime.plusHours(2)),
                FlightBuilder.createFlight(flightTime.minusMonths(9).plusHours(1), flightTime.minusMonths(7).plusHours(4)),
                // passedFlights expected section!!

                // disordered flights test expected section!!
                FlightBuilder.createFlight(flightTime, flightTime.plusHours(2),
                        flightTime.plusHours(4), flightTime.plusDays(1),
                        flightTime.minusDays(1).plusHours(10),flightTime),
                FlightBuilder.createFlight(flightTime.minusMonths(30), flightTime.plusHours(2),
                        flightTime.plusHours(5), flightTime.minusHours(10),
                        flightTime.minusHours(2),flightTime),
                FlightBuilder.createFlight(flightTime, flightTime.plusHours(2),
                        flightTime.minusHours(2), flightTime.plusDays(2),
                        flightTime.plusDays(3),flightTime.minusHours(1)),
                FlightBuilder.createFlight(flightTime.plusDays(3), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.plusHours(16), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.plusMonths(16), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.plusDays(3), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.plusHours(1), flightTime.plusHours(4),flightTime.plusDays(1),flightTime),
                FlightBuilder.createFlight(flightTime.minusMonths(2),flightTime.minusMonths(2).plusHours(2),
                        flightTime.minusMonths(1),flightTime.minusDays(30),
                        flightTime.minusHours(12),flightTime),
                // disordered flights test expected section!!

                FlightBuilder.createFlight(flightTime.plusHours(12), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.plusHours(12), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.plusHours(1), flightTime.plusHours(15)),
                FlightBuilder.createFlight(flightTime.plusHours(1), flightTime.plusHours(4)),
                FlightBuilder.createFlight(flightTime.minusMonths(2),flightTime.minusMonths(2).plusHours(2),
                        flightTime.minusMonths(1),flightTime.minusDays(15),
                        flightTime.minusHours(12),flightTime)
        );
    }

    // Segment time test data, used in FlightFilterHelpMethodsTest.java
    Collection<Object[]> SegmentTimeCountDataTest = Arrays.stream(new Object[][]{
            {LocalDateTime.now(), LocalDateTime.now().plusHours(12), 12},
            {LocalDateTime.now().minusHours(3), LocalDateTime.now().plusHours(12), 15},
            {LocalDateTime.now().minusDays(1), LocalDateTime.now().plusHours(1), 25},
            {LocalDateTime.now().minusMonths(6).minusDays(10).plusHours(12), LocalDateTime.now().plusHours(1), 4621},
            {LocalDateTime.now().minusMonths(12).plusDays(40).plusHours(12), LocalDateTime.now().plusMonths(10).plusHours(50),15134},
            {LocalDateTime.now(), LocalDateTime.now(),-1},
            {LocalDateTime.now().plusMonths(3).plusDays(1), LocalDateTime.now().minusDays(20),-1},
            {LocalDateTime.now().minusMonths(7).plusDays(10), LocalDateTime.now().minusMonths(4),1968},
            {LocalDateTime.now().minusMonths(7).plusDays(10), LocalDateTime.now().plusMonths(23),21696},
            {LocalDateTime.now().plusMonths(7).plusDays(10), LocalDateTime.now().plusMonths(23),11472},
            {LocalDateTime.now().plusMonths(0).plusDays(10), LocalDateTime.now().plusMonths(0),-1},
            {LocalDateTime.now().plusMonths(0).plusDays(0).plusHours(1), LocalDateTime.now().plusMonths(0).plusHours(0),-1}})
            .collect(Collectors.toList());
}
