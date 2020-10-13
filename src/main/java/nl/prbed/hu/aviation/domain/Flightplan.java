package nl.prbed.hu.aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
@Data
@AllArgsConstructor
public class Flightplan {
    private LocalTime duration;
    private Airport destination;
    private Airport arrival;
}
