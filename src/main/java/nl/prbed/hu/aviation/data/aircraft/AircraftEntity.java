package nl.prbed.hu.aviation.data.aircraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Fleet;
import nl.prbed.hu.aviation.domain.Flight;
import nl.prbed.hu.aviation.domain.Type;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "aircraft")
@AllArgsConstructor
@NoArgsConstructor
public class AircraftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private TypeEntity type;
}
