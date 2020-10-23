package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;

import java.util.List;

@Data
@AllArgsConstructor
public class Flight {
    private String code;
    private double priceEconomy;
    private double priceBusiness;
    private double priceFirst;
    private List<Booking> bookings;
    private Aircraft aircraft;
    private Flightplan flightplan;
}
