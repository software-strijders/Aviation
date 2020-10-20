package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Airport {
    private double latitude;
    private double longitude;
    private String code;
    private City city;
    private Fleet fleet;
}
