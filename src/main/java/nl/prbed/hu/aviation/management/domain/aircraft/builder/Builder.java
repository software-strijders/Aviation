package nl.prbed.hu.aviation.management.domain.aircraft.builder;

import nl.prbed.hu.aviation.management.domain.Flight;
import nl.prbed.hu.aviation.management.domain.Seat;
import nl.prbed.hu.aviation.management.domain.SeatType;
import nl.prbed.hu.aviation.management.domain.Type;
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