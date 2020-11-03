package nl.prbed.hu.aviation.management.data.booking;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode
@Table(name = "passenger")
public class PassengerEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String nationality;
    private String email;

    @OneToMany(mappedBy = "passenger")
    private List<FlightSeatEntity> flightSeats;

    public PassengerEntity() {}
    public PassengerEntity(
            String firstName,
            String lastName,
            LocalDate birthdate,
            String nationality,
            String email,
            List<FlightSeatEntity> flightSeats
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.email = email;
        this.flightSeats = flightSeats;
    }

    public void addFlightSeat(FlightSeatEntity entity) {
        this.flightSeats.add(entity);
    }
}
