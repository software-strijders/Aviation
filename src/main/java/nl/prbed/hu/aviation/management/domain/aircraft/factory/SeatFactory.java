package nl.prbed.hu.aviation.management.domain.aircraft.factory;

import nl.prbed.hu.aviation.management.data.aircraft.SeatEntity;
import nl.prbed.hu.aviation.management.domain.aircraft.Seat;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatFactory {
    public Seat from(SeatEntity entity) {
        return new Seat(entity.getSeatType(), null, entity.getSeatNumber());
    }

    public List<Seat> from(int num, SeatType type) {
        var seats = new ArrayList<Seat>();
        for(int i = 0; i < num; i++) {
            seats.add(new Seat(type, null, i));
        }
        return seats;
    }
}
