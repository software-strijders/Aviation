package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String modelName;
    private String manufacturer;
    private int fuelCapacity;
    private int fuelConsumption;
    private int numSeatsFirst;
    private int numSeatsBusiness;
    private int numSeatsEconomy;

    public TypeEntity() {}
    public TypeEntity(
            String modelName,
            String manufacturer,
            int fuelCapacity,
            int fuelConsumption,
            int numSeatsFirst,
            int numSeatsBusiness,
            int numSeatsEconomy)
    {
        this.modelName = modelName;
        this.manufacturer = manufacturer;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
        this.numSeatsFirst = numSeatsFirst;
        this.numSeatsBusiness = numSeatsBusiness;
        this.numSeatsEconomy = numSeatsEconomy;
    }
}
