package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.domain.Seat;
import nl.prbed.hu.aviation.management.domain.SeatType;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "seat")
public class SeatEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private SeatType seatType;

    @OneToOne(cascade=CascadeType.ALL)
    private PassengerEntity passenger;

    public SeatEntity(){}
    public SeatEntity(SeatType seatType, PassengerEntity passenger) {
        this.seatType = seatType;
        this.passenger = passenger;
    }
}
