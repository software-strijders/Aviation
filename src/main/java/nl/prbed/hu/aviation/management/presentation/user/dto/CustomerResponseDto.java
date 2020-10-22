package nl.prbed.hu.aviation.management.presentation.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class CustomerResponseDto {
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String nationality;
    private final LocalDate birthdate;
    private final String email;
    private final int phoneNumber;

//    TODO: Add bookings to user
//    private List<Booking> bookings;
}
