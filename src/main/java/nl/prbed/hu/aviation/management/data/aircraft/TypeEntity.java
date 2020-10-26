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

    public TypeEntity() {}
    public TypeEntity(
            String modelName,
            String manufacturer,
            int fuelCapacity,
            int fuelConsumption
    ) {
        this.modelName = modelName;
        this.manufacturer = manufacturer;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
    }
}
