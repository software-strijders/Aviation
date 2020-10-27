package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.*;
import javax.persistence.*;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
import nl.prbed.hu.aviation.management.domain.Seat;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "aircraft")
public class AircraftEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private TypeEntity type;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SeatEntity> seats;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    private AirportEntity airport;

    public AircraftEntity() {}
    public AircraftEntity(String code, TypeEntity type, List<SeatEntity> seats, AirportEntity airport) {
        this.code = code;
        this.type = type;
        this.seats = seats;
        this.airport = airport;
    }
}
