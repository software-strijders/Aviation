package nl.prbed.hu.aviation.management.domain.builder;

import nl.prbed.hu.aviation.management.domain.Aircraft;
import nl.prbed.hu.aviation.management.domain.Flight;
import nl.prbed.hu.aviation.management.domain.Seat;
import nl.prbed.hu.aviation.management.domain.Type;

import java.util.List;

public interface Builder {
    Builder code(String code);
    Builder firstSeats(int num);
    Builder businessSeats(int num);
    Builder economySeats(int num);
    Builder seats(List<Seat> seats);
    Builder type(Type type);
    Builder flight(Flight flight);
    Aircraft build();
}
