package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.aircraft.SeatEntity;
import nl.prbed.hu.aviation.management.domain.Seat;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeatFactory {
    private final PassengerFactory passengerFactory;

    public Seat from(SeatEntity entity) {
        return new Seat(entity.getSeatType(), passengerFactory.from(entity.getPassenger()));
    }
}
