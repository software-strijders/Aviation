package nl.prbed.hu.aviation.data.airport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String name;
    private String country;

    @OneToMany(mappedBy = "city")
    private List<AirportEntity> airportEntities;

    public void addAirport(AirportEntity entity) {
        airportEntities.add(entity);
    }
}
