package nl.prbed.hu.aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    private String name;
    private String country;
    private Airport airport;
}
