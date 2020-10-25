package nl.prbed.hu.aviation.management.domain.factory;

import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.domain.Passenger;
import nl.prbed.hu.aviation.management.domain.Seat;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightSeatFactory {
    public Seat from(FlightSeatEntity entity) {
        var passengerEntity = entity.getPassenger();
        Passenger passenger = null;
        if (passengerEntity != null) {
            passenger = new Passenger(
                    passengerEntity.getFirstName(),
                    passengerEntity.getLastName(),
                    passengerEntity.getBirthdate(),
                    passengerEntity.getNationality(),
                    null,
                    null
            );
        }
        return new Seat(entity.getSeat().getSeatType(), passenger);
    }

    public List<Seat> from(List<FlightSeatEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
