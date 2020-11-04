package nl.prbed.hu.aviation.management.domain.flight.factory;

import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.domain.booking.Passenger;
import nl.prbed.hu.aviation.management.domain.aircraft.Seat;
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
                    passengerEntity.getEmail(),
                    null,
                    null
            );
        }
        return new Seat(entity.getSeat().getSeatType(), passenger, entity.getSeat().getSeatNumber());
    }

    public List<Seat> from(List<FlightSeatEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
