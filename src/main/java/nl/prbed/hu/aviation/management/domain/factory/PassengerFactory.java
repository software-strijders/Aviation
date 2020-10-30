package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.booking.PassengerEntity;
import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.domain.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PassengerFactory {
    private final SeatFactory seatFactory;

    public Passenger from(PassengerEntity entity) {
        return new Passenger(
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthdate(),
                entity.getNationality(),
                entity.getEmail(),
                entity.getFlightSeats().stream()
                        .map(FlightSeatEntity::getSeat)
                        .map(seatFactory::from)
                        .collect(Collectors.toList()),
                null
        );
    }

    public List<Passenger> from(List<PassengerEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
