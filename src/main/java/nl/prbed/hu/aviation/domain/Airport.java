package nl.prbed.hu.aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Airport {
    private float latitude;
    private float longitude;
    private String code;
    private City city;
    private Fleet fleet;
}
