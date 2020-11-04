package nl.prbed.hu.aviation.management.application.filters;

import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.List;
import java.util.Map;

public interface Filter {
    List<Flight> filter(List<Flight> flights, Map<String, String> searchDetails);
}
