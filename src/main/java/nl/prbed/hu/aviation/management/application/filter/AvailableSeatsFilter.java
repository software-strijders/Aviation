package nl.prbed.hu.aviation.management.application.filter;

import lombok.NoArgsConstructor;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AvailableSeatsFilter implements Filter {
    @Override
    public List<Flight> filter(List<Flight> flights, Map<String, String> searchDetails) {
        return flights.stream()
                .filter(f -> f.areSeatsAvailable(
                    SeatType.fromString(searchDetails.get("flightClass")),
                    Integer.parseInt(searchDetails.get("passengers")))
                )
                .collect(Collectors.toList());
    }
}
