package nl.prbed.hu.aviation.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "flightplan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FlightPlanEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalTime duration;

    @OneToOne
    private AirportEntity arrival;

    @OneToOne
    private AirportEntity destination;
}
