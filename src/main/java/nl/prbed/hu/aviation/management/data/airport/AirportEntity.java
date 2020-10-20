package nl.prbed.hu.aviation.management.data.airport;

import lombok.Getter;
import lombok.Setter;

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
    private double latitude;
    private double longitude;

    public AirportEntity() {}
    public AirportEntity(String code, double latitude, double longitude, CityEntity city) {
        this.code = code;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    @ManyToOne
    private CityEntity city;
}
