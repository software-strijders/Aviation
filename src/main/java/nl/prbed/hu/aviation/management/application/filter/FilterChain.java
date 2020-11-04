package nl.prbed.hu.aviation.management.application.filter;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class FilterChain {
    private List<Flight> flights;
    private final Map<String, String> searchDetails;
    private final List<Filter> filters = new ArrayList<>();

    public List<Flight> doFilters() {
        filters.forEach(filter -> flights = filter.filter(flights, searchDetails));
        return flights;
    }

    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }
}
