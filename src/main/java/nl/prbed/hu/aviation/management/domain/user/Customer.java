package nl.prbed.hu.aviation.management.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.domain.booking.Booking;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class Customer {
    private Long userId;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate birthdate;
    private String email;
    private String phoneNumber;
    private List<Booking> bookings;
}
