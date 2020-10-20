package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Booking {
    private double price;
    private Customer customer;
    private Flight flight; //Probably should be list, because 1 booking can have multiple flights if there's a transfer.
    private List<Passenger> passenger;
}
