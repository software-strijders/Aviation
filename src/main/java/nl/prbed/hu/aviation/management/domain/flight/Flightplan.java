package nl.prbed.hu.aviation.management.domain.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.airport.Airport;

@Data
@AllArgsConstructor
public class Flightplan {
    private String code;
    private Long duration;
    private Airport destination;
    private Airport departure;
}
