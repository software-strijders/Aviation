package nl.prbed.hu.aviation.data.flightplan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;

import javax.persistence.*;

@Entity
@Table(name = "flightplan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}
