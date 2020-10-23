package nl.prbed.hu.aviation.management.data.flight;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.flightplan.FlightplanEntity;
import nl.prbed.hu.aviation.management.domain.Aircraft;
import nl.prbed.hu.aviation.management.domain.Booking;
import nl.prbed.hu.aviation.management.domain.Flightplan;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "flight")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String code;
    private double priceEconomy;
    private double priceBusiness;
    private double priceFirst;

    //TODO: Implement this #222
    // private List<BookingEntity> bookings;

    @OneToOne
    private AircraftEntity aircraft;
    @OneToOne
    private FlightplanEntity flightplan;

    public FlightEntity(String code, double priceEconomy, double priceBusiness, double priceFirst,
                        AircraftEntity aircraft, FlightplanEntity flightplan) {
        this.code = code;
        this.priceEconomy = priceEconomy;
        this.priceBusiness = priceBusiness;
        this.priceFirst = priceFirst;
        this.aircraft = aircraft;
        this.flightplan = flightplan;
    }
}
