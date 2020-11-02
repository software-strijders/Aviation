package nl.prbed.hu.aviation.management.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.Customer;
import nl.prbed.hu.aviation.management.domain.Passenger;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.List;

@Data
@AllArgsConstructor
public class Booking {
    private double price;
    private boolean confirmed;
    private Customer customer;
    private Flight flight;
    private List<Passenger> passengers;
}
