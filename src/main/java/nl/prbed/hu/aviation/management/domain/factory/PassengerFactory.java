package nl.prbed.hu.aviation.management.domain.factory;

import nl.prbed.hu.aviation.management.data.aircraft.PassengerEntity;
import nl.prbed.hu.aviation.management.domain.Passenger;
import org.springframework.stereotype.Component;

@Component
public class PassengerFactory {
    public Passenger from(PassengerEntity entity) {
        return new Passenger(
                entity.getFirstName(),
                entity.getSurName(),
                entity.getBirthdate(),
                entity.getNationality(),
                null,
                null
        );
    }
}
