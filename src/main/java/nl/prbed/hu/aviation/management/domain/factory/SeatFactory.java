package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.aircraft.SeatEntity;
import nl.prbed.hu.aviation.management.domain.Seat;
import nl.prbed.hu.aviation.management.domain.SeatType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatFactory {
    private final PassengerFactory passengerFactory;

    public Seat from(SeatEntity entity) {
        return new Seat(entity.getSeatType(), null);
    }

    public List<Seat> from(int num, SeatType type) {
        var seats = new ArrayList<Seat>();
        for(int i = 0; i < num; i++) {
            seats.add(new Seat(type, null));
        }
        return seats;
    }
}
