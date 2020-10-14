package nl.prbed.hu.aviation.data.airport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airport")
public class AirportEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;
    private Float longitude;
    private Float latitude;

    //TODO fix CityEntity
//    @OneToOne
//    private CityEntity city;
}
