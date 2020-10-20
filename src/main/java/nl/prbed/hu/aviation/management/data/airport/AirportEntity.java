package nl.prbed.hu.aviation.management.data.airport;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "airport")
public class AirportEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;
    private double latitude;
    private double longitude;

    @ManyToOne
    private CityEntity city;

    @OneToMany
    private List<AircraftEntity> aircraftEntities;

    public AirportEntity() {}
    public AirportEntity(String code, double latitude, double longitude, CityEntity city, List<AircraftEntity> aircraftEntities) {
        this.code = code;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.aircraftEntities = aircraftEntities;
    }
}
