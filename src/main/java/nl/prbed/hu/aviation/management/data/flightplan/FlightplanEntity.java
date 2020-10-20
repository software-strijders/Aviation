package nl.prbed.hu.aviation.management.data.flightplan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "flightplan")
public class FlightplanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String code;

    @Column
    private Long duration;

    @OneToOne
    private AirportEntity arrival;

    @OneToOne
    private AirportEntity destination;

    public FlightplanEntity() {}
    public FlightplanEntity(String code, Long duration, AirportEntity arrival, AirportEntity destination) {
        this.code = code;
        this.duration = duration;
        this.arrival = arrival;
        this.destination = destination;
    }
}
