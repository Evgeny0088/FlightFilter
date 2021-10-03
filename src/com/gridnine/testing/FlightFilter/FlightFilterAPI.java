package com.gridnine.testing.FlightFilter;

import com.gridnine.testing.Flights.Flight;

import java.util.List;
import java.util.Map;

public interface FlightFilterAPI {
    /**
     * @return Map object of name and description for each filter in FlightFilter.java: command tag -> description.
     This item should be used in FilterRun.run method where options shown in console.
     */
    Map<String, String> filter_description();

    /**
     * @return List of flights available at the common Flight list
     */
    List<Flight> allFlights(List<Flight> flights);

    /**
    * @return List of flights where arrival time earlier or equals than departure time
    */
    List<Flight> disorderedFlights(List<Flight> flights);

    /**
    * @return This function excludes all disordered flights before filtering of any other flights.
    Method creates temporarily copy list of disordered flights and removes them
    */
    List<Flight> ExcludeDisorderedFlights(List<Flight> flights);

    /**
    * @return List of flights where departure before or equals than current time
     */
    List<Flight> passedFlights(List<Flight> flights);

    /**
    * @return List of flights where ground time is longer than 2 hours
     */
    List<Flight> LongestGroundTimeFlights(List<Flight> flights);

    /**
    * @return Singleton list of the fastest flight
     */
    List<Flight> fastestFlight(List<Flight> flights);

    /**
    * @return Singleton list of the longest flight
     */
    List<Flight> longestFlight(List<Flight> flights);
}
