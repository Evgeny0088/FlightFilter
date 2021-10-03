package com.gridnine.testing.FlightFilter;

import com.gridnine.testing.Flights.Flight;
import com.gridnine.testing.Flights.FlightsRepo;
import com.gridnine.testing.utils.UtilFunctions;
import java.util.*;
import java.util.stream.Collectors;

public class FilterRun {

    private static final List<Flight> flights = FlightsRepo.createFlights();

    public void run(){
        FlightFilter filter = FlightFilter.getFilter();
        Map<String,String> filterOptions = filter.filter_description();
        System.out.println("### WELCOME TO FLIGHT FILTER ###");
        System.out.println("Please enter one of the available options:\n");

        String options_representation = filterOptions.entrySet().stream().map(entry-> {
                    String tag = entry.getKey();
                    String desc = entry.getValue();
                    return "- " + tag + " => " + desc;}).collect(Collectors.joining("\n\n"));
        System.out.println(options_representation);

        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nOption: ");
            String option = scanner.nextLine().toLowerCase();
            if (!UtilFunctions.inputCheck(option,filterOptions.keySet())){
                continue;
            }
            else
            {
                switch (option) {
                    case "a" -> {
                        List<Flight> allFlights = filter.allFlights(flights);
                        for (int i = 0; i < allFlights.size(); i++) {
                            System.out.println("Flight" + " #" + i + " =>>" + allFlights.get(i));
                        }
                    }
                    case "p" -> {
                        List<Flight> passedFlights = filter.passedFlights(flights);
                        System.out.println("Flights were departure before current time:");
                        UtilFunctions.FlightsRepresentation(passedFlights,flights);
                    }
                    case "di" -> {
                        List<Flight> disorderedFlights = filter.disorderedFlights(flights);
                        System.out.println("Disordered flights (wrong time order):");
                        UtilFunctions.FlightsRepresentation(disorderedFlights,flights);
                    }
                    case "l" -> {
                        List<Flight> longestGroundTimeFlights = filter.LongestGroundTimeFlights(flights);
                        System.out.println("Flights with ground time more than 2 hours:");
                        UtilFunctions.FlightsRepresentation(longestGroundTimeFlights,flights);
                    }
                    case "f" -> {
                        List<Flight> fastest = filter.fastestFlight(flights);
                        System.out.println("\nFastest flight:");
                        UtilFunctions.FlightsRepresentation(fastest,flights);
                    }
                    case "lst" -> {
                        List<Flight> longest = filter.longestFlight(flights);
                        System.out.println("\nLongest flight:");
                        UtilFunctions.FlightsRepresentation(longest,flights);
                    }
                    case "exit" -> {
                        System.out.println("You finished flight filtering, bye!");
                        System.exit(0);
                    }
                }
            }
        }
    }
}
