package nl.prbed.hu.aviation.management.domain.builder;

import nl.prbed.hu.aviation.management.domain.Aircraft;
import nl.prbed.hu.aviation.management.domain.Flight;
import nl.prbed.hu.aviation.management.domain.Type;

public interface Builder {
    Builder code(String code);
    Builder firstSeats(int num);
    Builder businessSeats(int num);
    Builder economySeats(int num);
    Builder type(Type type);
    Builder flight(Flight flight);
    Aircraft build();
}
