package nl.prbed.hu.aviation.management.domain.factory;

import nl.prbed.hu.aviation.management.data.booking.PassengerEntity;
import nl.prbed.hu.aviation.management.domain.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassengerFactory {
    public Passenger from(PassengerEntity entity) {
        return new Passenger(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthdate(),
                entity.getNationality(),
                null,
                null
        );
    }

    public List<Passenger> from(List<PassengerEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
