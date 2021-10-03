package com.gridnine.testing.utils;

import com.gridnine.testing.Flights.Flight;
import com.gridnine.testing.Flights.Segment;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UtilFunctions {

    /**
     * Calculating time between two dates, returns -1 if departure date later or equals than arrival date
     * @param departure departure date
     * @param arrival arrival date
     * @return int
     */
    public static int SegmentTimeCount(LocalDateTime departure, LocalDateTime arrival) {
        if (arrival.isBefore(departure)) return -1;
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
        List<Integer> time1 = Arrays.stream(departure.format(fmt).split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> time2 = Arrays.stream(arrival.format(fmt).split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int years = time2.get(0) - time1.get(0);
        int month_departure = time1.get(1);
        int month_arrival = time2.get(1);
        int day_departure = time1.get(2);
        int day_arrival = time2.get(2);
        int daysCountOfParticularMonth = YearMonth.of(time2.get(0),(month_arrival-1)==0 ? 12 : month_arrival-1).lengthOfMonth();
        int days = 0;
        int months = 0;
        if (day_arrival<=day_departure){
            days = (daysCountOfParticularMonth-day_departure) + day_arrival;
            months = (12-month_departure) + month_arrival + days/(daysCountOfParticularMonth) -1;
        }
        if (day_arrival>day_departure){
            months = (12 - month_departure) + month_arrival;
            days = day_arrival-day_departure;
        }
        if (month_arrival<=month_departure){
            years = years -1 + months/12;
        }

        days = days%daysCountOfParticularMonth;
        months = months%12;

        int yearIncreament = 0;
        int monthsCount = years*12 + months;
        for (int i=0;i<monthsCount;i++){
            if ((month_departure)>12){
                month_departure = 1;
                yearIncreament++;
            }
            days+=YearMonth.of(time1.get(0)+yearIncreament,month_departure++).lengthOfMonth();
        }
        int hours = days*24 + time2.get(3)-time1.get(3);
        return hours>0 ? hours : -1;
    }

    /**
     * Flight time definition for fast comparison between two flights
     * @return int
     */
    private static int flightTime(Flight flight){
        int hours;
        List<Segment> segments = flight.getSegments();
        hours = SegmentTimeCount(segments.get(0).getDepartureDate(),segments.get(segments.size()-1).getArrivalDate());
        return hours;
    }

    /**
     * Custom Flights comparator. Comparing flights by flight time.
    */
    public static final Comparator<Flight> flightTimeComparator = Comparator.comparingInt(UtilFunctions::flightTime);

    /**
     * Prints filtered flights in represented way:
     * Number of flight(#) is index from list of available flights in repository
    */
    public static void FlightsRepresentation(List<Flight> sortedFlights, List<Flight> allFlights) {
        for (Flight f : sortedFlights) {
            int flightIndex = allFlights.indexOf(f);
            System.out.println("Flight" + " #" + flightIndex + " =>>" + allFlights.get(flightIndex));
        }
    }

    /**
     * This function verifies if user inputs equals any filter description options.
     */
    public static boolean inputCheck(String input, Set<String> options){
        if (!options.contains(input)){
            System.out.printf("you typed: %s. That option does not exits, please try again...%n",input);
            return false;
        }
        return true;
    }
}
