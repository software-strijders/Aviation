package nl.prbed.hu.aviation.domain;

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
    private int numSeatsFirst;
    private int numSeatsBusiness;
    private int numSeatsEconomy;
}
