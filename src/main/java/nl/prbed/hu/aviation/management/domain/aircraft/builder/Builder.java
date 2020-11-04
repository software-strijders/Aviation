package nl.prbed.hu.aviation.management.domain.aircraft.builder;

import nl.prbed.hu.aviation.management.domain.flight.Flight;
import nl.prbed.hu.aviation.management.domain.aircraft.Seat;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import nl.prbed.hu.aviation.management.domain.aircraft.Type;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;

import java.util.List;

public interface Builder {
    Builder code(String code);
    Builder addSeats(int num, SeatType type);
    Builder seats(List<Seat> seats);
    Builder type(Type type);
    Builder flight(Flight flight);
    Aircraft build();
}
