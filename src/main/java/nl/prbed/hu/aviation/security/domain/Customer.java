package nl.prbed.hu.aviation.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Customer extends User {
    private String nationality;
    private LocalDate birthdate;
    private String email;
    private int phoneNumber;
    private List<Booking> bookings;
}
