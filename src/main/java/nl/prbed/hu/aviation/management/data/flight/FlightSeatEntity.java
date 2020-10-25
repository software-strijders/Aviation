package nl.prbed.hu.aviation.management.data.flight;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.aircraft.SeatEntity;
import nl.prbed.hu.aviation.management.data.booking.PassengerEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "flight_seats")
public class FlightSeatEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private SeatEntity seat;

    @ManyToOne
    @JoinColumn
    private PassengerEntity passenger;

    @ManyToOne
    @JoinColumn
    private FlightEntity flight;

    public FlightSeatEntity() {}
    public FlightSeatEntity(SeatEntity seat, PassengerEntity passenger, FlightEntity flight) {
        this.seat = seat;
        this.passenger = passenger;
        this.flight = flight;
    }
}
