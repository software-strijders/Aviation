package nl.prbed.hu.aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Flight {
    private double priceEconomy;
    private double priceBusiness;
    private double priceFirst;
    private List<Booking> bookings;
    private Aircraft aircraft;
    private Flightplan flightplan;
}
