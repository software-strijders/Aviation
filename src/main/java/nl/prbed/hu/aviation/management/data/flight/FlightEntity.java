package nl.prbed.hu.aviation.management.data.flight;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.booking.BookingEntity;
import nl.prbed.hu.aviation.management.data.flightplan.FlightplanEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "flight")
public class FlightEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;
    private double priceEconomy;
    private double priceBusiness;
    private double priceFirst;

    @OneToMany
    private List<BookingEntity> bookings;

    @OneToOne
    private AircraftEntity aircraft;

    @OneToOne
    private FlightplanEntity flightplan;

    @OneToMany(mappedBy = "flight")
    private List<FlightSeatEntity> flightSeats;

    public FlightEntity() {}
    public FlightEntity(
            String code,
            double priceEconomy,
            double priceBusiness,
            double priceFirst,
            List<BookingEntity> bookings,
            AircraftEntity aircraft,
            FlightplanEntity flightplan,
            List<FlightSeatEntity> flightSeats
    ) {
        this.code = code;
        this.priceEconomy = priceEconomy;
        this.priceBusiness = priceBusiness;
        this.priceFirst = priceFirst;
        this.bookings = bookings;
        this.aircraft = aircraft;
        this.flightplan = flightplan;
        this.flightSeats = flightSeats;
    }
}
