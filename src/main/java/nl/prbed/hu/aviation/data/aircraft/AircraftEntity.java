package nl.prbed.hu.aviation.data.aircraft;

import lombok.*;
import nl.prbed.hu.aviation.domain.Fleet;
import nl.prbed.hu.aviation.domain.Flight;
import nl.prbed.hu.aviation.domain.Type;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "aircraft")
@AllArgsConstructor
@NoArgsConstructor
public class AircraftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter private Long id;

    @Column(unique = true)
    @Getter private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @Getter private TypeEntity type;
}
