package nl.prbed.hu.aviation.data.aircraft;

import lombok.*;
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "id")
    @Getter private TypeEntity type;
}
