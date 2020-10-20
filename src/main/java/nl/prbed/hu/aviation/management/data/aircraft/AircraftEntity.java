package nl.prbed.hu.aviation.management.data.aircraft;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public AircraftEntity() {}
    public AircraftEntity(String code, TypeEntity type) {
        this.code = code;
        this.type = type;
    }
}
