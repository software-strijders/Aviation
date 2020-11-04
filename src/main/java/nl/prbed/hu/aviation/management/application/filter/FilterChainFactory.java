package nl.prbed.hu.aviation.management.application.filter;

import nl.prbed.hu.aviation.management.domain.flight.Flight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FilterChainFactory {
    public FilterChain create(List<Flight> flights, Map<String, String> searchDetails) {

        var filterChain = new FilterChain(flights, searchDetails);
        if (searchDetails.containsKey("passengers") && searchDetails.containsKey("flightClass")) {
            filterChain.addFilter(new SeatsAvailableFilter());
        }

        //departure
        if (searchDetails.containsKey("from")) {
            filterChain.addFilter(new DepartureFilter());
        }

        //destination
        if (searchDetails.containsKey("to")) {
            filterChain.addFilter(new ArrivalFilter());
        }

        //date
        if (searchDetails.containsKey("date")) {
            filterChain.addFilter(new DateFilter());
        }
        return filterChain;
    }
}
