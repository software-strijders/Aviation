package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;

import java.util.List;

@Data
@AllArgsConstructor
public class Airport {
    private double latitude;
    private double longitude;
    private String code;
    private City city;
    private List<Aircraft> aircraft;
}
