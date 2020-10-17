package nl.prbed.hu.aviation.data.airport;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.domain.Airport;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "airport")
public class AirportEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;
    private Float longitude;
    private Float latitude;

    public AirportEntity() {}
    public AirportEntity(String code, Float longitude, Float latitude, CityEntity city) {
        this.code = code;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    @ManyToOne
    private CityEntity city;
}
