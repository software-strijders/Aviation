package nl.prbed.hu.aviation.management.domain.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.aircraft.Seat;
import nl.prbed.hu.aviation.management.domain.booking.Booking;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Passenger {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String nationality;
    private String email;
    private List<Seat> seats;
    private List<Booking> bookings;
}
