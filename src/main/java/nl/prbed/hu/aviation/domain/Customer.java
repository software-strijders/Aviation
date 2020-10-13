package nl.prbed.hu.aviation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {
    private String nationality;
    private LocalDate birthdate;
    private String email;
    private int phoneNumber;
    private List<Booking> bookings;
}
