package com.gridnine.testing.Flights;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Flight repository where stored all available flights
 */
public interface FlightsRepo {

    LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    static List<Flight> createFlights() {
        return Arrays.asList(
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                FlightBuilder.createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow.minusDays(6).plusHours(2)),
                FlightBuilder.createFlight(threeDaysFromNow.minusDays(5), threeDaysFromNow.minusDays(5).plusHours(10)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(12)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusDays(1).plusHours(6)),
                FlightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(0), threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)));
    }
}
