package nl.prbed.hu.aviation.management.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.user.Customer;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.List;

@Data
@AllArgsConstructor
public class Booking {
    private Long id;
    private double price;
    private boolean confirmed;
    private Customer customer;
    private Flight flight;
    private List<Passenger> passengers;
}
