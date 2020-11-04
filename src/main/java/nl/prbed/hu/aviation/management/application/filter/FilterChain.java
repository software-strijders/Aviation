package nl.prbed.hu.aviation.management.application.filters;

import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.List;
import java.util.Map;

public class FilterChain {
    public List<Flight> doFilters(List<Flight> flights, Map<String, String> searchDetails) {
        //seats available
        if (searchDetails.containsKey("passengers") && searchDetails.containsKey("flightClass")) {
            flights = new SeatsAvailableFilter().filter(flights, searchDetails);
        }

        //departure
        if (searchDetails.containsKey("from")) {
            flights = new DepartureFilter().filter(flights, searchDetails);
        }

        //destination
        if (searchDetails.containsKey("to")) {
            flights = new ArrivalFilter().filter(flights, searchDetails);
        }

        //date
        if (searchDetails.containsKey("date")) {
            flights = new DateFilter().filter(flights, searchDetails);
        }
        return flights;
    }
}
