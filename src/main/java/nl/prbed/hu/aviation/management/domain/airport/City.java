package nl.prbed.hu.aviation.management.domain.airport;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class City {
    private String name;
    private String country;
    private List<Airport> airports;
}
