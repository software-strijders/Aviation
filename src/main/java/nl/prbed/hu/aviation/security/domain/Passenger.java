package nl.prbed.hu.aviation.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Passenger {
    private String firstName;
    private String surName;
    private LocalDate birtdate;
    private String nationality;
    private List<Booking> bookings;
    private Seat seat;
}
