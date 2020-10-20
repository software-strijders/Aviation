package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.*;
import javax.persistence.*;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    @Getter private AirportEntity airport;

    public AircraftEntity() {}
    public AircraftEntity(String code, TypeEntity type, AirportEntity airport) {
        this.code = code;
        this.type = type;
        this.airport = airport;
    }
}
