package nl.prbed.hu.aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Flightplan {
    private String code;
    private Long duration;
    //TODO: Fix naming issues
    private Airport destination;
    private Airport arrival;
}
