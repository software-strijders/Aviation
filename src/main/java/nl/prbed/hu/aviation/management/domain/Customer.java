package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    private int phoneNumber;
    private List<Booking> bookings;
}
