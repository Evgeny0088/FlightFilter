package com.gridnine.testing.FlightFilter;

import com.gridnine.testing.Flights.Flight;
import com.gridnine.testing.Flights.Segment;
import com.gridnine.testing.utils.UtilFunctions;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightFilter implements FlightFilterAPI {

    private static FlightFilter filter;

    public static synchronized FlightFilter getFilter(){
        if (filter==null){
            filter = new FlightFilter();
        }
        return filter;
    }

    @Override
    public Map<String, String> filter_description() {
        LinkedHashMap<String, String> filterOptions = new LinkedHashMap<>();
        filterOptions.put("a", "prints out all flights available at the pool");
        filterOptions.put("p", "prints out flights where departure before current time");
        filterOptions.put("di", "prints out flights where arrival time earlier/equals than departure time");
        filterOptions.put("l", "prints out flights where common ground time is longer than 2 hours");
        filterOptions.put("f", "This method should print fastest flight with duration time");
        filterOptions.put("lst", "This method should print longest flight with duration time");
        filterOptions.put("exit", "Safety exit from program");
        return filterOptions;
    }

    @Override
    public List<Flight> allFlights(List<Flight> flights) {
        return flights;
    }

    @Override
    public List<Flight> disorderedFlights(List<Flight> flights) {
        List<Flight> disorderedFlights = new ArrayList<>();
        for (Flight f: flights){
            LocalDateTime departureTime = f.getSegments().get(0).getDepartureDate();
            LinkedList<LocalDateTime> collectedSegmentsTime = new LinkedList<>();
            for (Segment s: f.getSegments()){
                collectedSegmentsTime.add(s.getDepartureDate());
                collectedSegmentsTime.add(s.getArrivalDate());
            }
            for (int i=1;i<collectedSegmentsTime.size();i++){
                if (departureTime.isBefore(collectedSegmentsTime.get(i))){
                    departureTime = collectedSegmentsTime.get(i);
                }else {
                    disorderedFlights.add(f);
                    break;
                }
            }
        }
        return disorderedFlights;
    }

    @Override
    public List<Flight> ExcludeDisorderedFlights(List<Flight> flights){
        List<Flight> flightsCopy = new ArrayList<>(flights);
        flightsCopy.removeAll(disorderedFlights(flights));
        return flightsCopy;
    }
    @Override
    public List<Flight> passedFlights(List<Flight> flights) {
        List<Flight> flightsCopy = ExcludeDisorderedFlights(flights);
        Predicate<Segment> byDate = segment -> segment.getDepartureDate().isBefore(LocalDateTime.now());
        return flightsCopy.stream().filter(f-> f.getSegments().stream().anyMatch(byDate)).collect(Collectors.toList());
    }

    @Override
    public List<Flight> LongestGroundTimeFlights(List<Flight> flights) {
        List<Flight> flightsCopy = ExcludeDisorderedFlights(flights);
        Predicate<Flight> groundTime = flight -> {
            int hours = 0;
            List<Segment> segmentList = flight.getSegments();
            for (int i = 0; i < segmentList.size() - 1; i++) {
                hours += UtilFunctions.SegmentTimeCount(segmentList.get(i).getArrivalDate(),segmentList.get(i + 1).getDepartureDate());
                if (hours > 2) {
                    return true;
                }
            }
            return false;
        };
        return flightsCopy.stream().filter(groundTime).collect(Collectors.toList());
    }

    @Override
    public List<Flight> fastestFlight(List<Flight> flights) {
        List<Flight> flightsCopy = ExcludeDisorderedFlights(flights);
        Flight f = flightsCopy.stream().min(UtilFunctions.flightTimeComparator).get();
        return Collections.singletonList(f);
    }

    @Override
    public List<Flight> longestFlight(List<Flight> flights) {
        List<Flight> flightsCopy = ExcludeDisorderedFlights(flights);
        Flight f = flightsCopy.stream().max(UtilFunctions.flightTimeComparator).get();
        return Collections.singletonList(f);
    }
}