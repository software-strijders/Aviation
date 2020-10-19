package nl.prbed.hu.aviation.data.airport;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String name;
    private String country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<AirportEntity> airportEntities;

    public CityEntity() {}
    public CityEntity(String name, String country, List<AirportEntity> airportEntities) {
        this.name = name;
        this.country = country;
        this.airportEntities = airportEntities;
    }

    public void addAirport(AirportEntity entity) {
        airportEntities.add(entity);
    }
}
