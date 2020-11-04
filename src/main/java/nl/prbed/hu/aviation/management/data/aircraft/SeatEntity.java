package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "seat")
public class SeatEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private SeatType seatType;
    private int seatNumber;

    @OneToMany(mappedBy = "seat")
    private List<FlightSeatEntity> flightSeats;

    public SeatEntity(){}
    public SeatEntity(SeatType seatType, List<FlightSeatEntity> flightSeats, int seatNumber) {
        this.seatType = seatType;
        this.flightSeats = flightSeats;
        this.seatNumber = seatNumber;
    }
}
