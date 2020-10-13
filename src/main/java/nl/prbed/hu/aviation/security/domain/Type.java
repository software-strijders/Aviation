package nl.prbed.hu.aviation.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Type {
    private String modelName;
    private String manufacturer;
    private int fuelCapacity;
    private int fuelConsumption;
    private int numSeats;
    private List<Seat> seats;
}
