package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.domain.Booking;
import nl.prbed.hu.aviation.management.domain.Seat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "passenger")
public class PassengerEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;
    private String surName;
    private LocalDate birthdate;
    private String nationality;

    //@OneToOne
    //private Booking booking;

    @OneToOne
    private SeatEntity seat;
}
