package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "type")
@AllArgsConstructor
@NoArgsConstructor
public class TypeEntity {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column
    @Getter private String modelName;
    @Getter private String manufacturer;
    @Getter private int fuelCapacity;
    @Getter private int fuelConsumption;
    @Getter private int numSeatsFirst;
    @Getter private int numSeatsBusiness;
    @Getter private int numSeatsEconomy;
}
