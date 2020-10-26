package nl.prbed.hu.aviation.management.domain.aircraft;

import lombok.*;
import nl.prbed.hu.aviation.management.domain.flight.Flight;
import nl.prbed.hu.aviation.management.domain.Seat;
import nl.prbed.hu.aviation.management.domain.Type;
import nl.prbed.hu.aviation.management.domain.aircraft.builder.AircraftBuilder;
import nl.prbed.hu.aviation.management.domain.aircraft.builder.Builder;
import nl.prbed.hu.aviation.management.domain.factory.PassengerFactory;
import nl.prbed.hu.aviation.management.domain.factory.SeatFactory;

import java.util.List;

@Data
@AllArgsConstructor
public class Aircraft {
    private String code;
    private Type type;

    // TODO: These are not doing anyting yet:
    private List<Seat> seats;
    private Flight current;
    private List<Flight> past;

    public static Builder create() {
        return new AircraftBuilder(new SeatFactory(new PassengerFactory()));
    }
}
