package nl.prbed.hu.aviation.management.domain.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.Airport;

@Data
@AllArgsConstructor
public class Flightplan {
    private String code;
    private Long duration;
    //TODO: Fix naming issues
    private Airport destination;
    private Airport arrival;
}
