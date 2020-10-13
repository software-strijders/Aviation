package nl.prbed.hu.aviation.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Airport {
    private float latitude;
    private float longitude;
    private float code;
    private City city;
    private Fleet fleet;
}
