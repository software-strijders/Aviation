package nl.prbed.hu.aviation.management.domain;

import lombok.*;
import nl.prbed.hu.aviation.management.domain.builder.AircraftBuilder;
import nl.prbed.hu.aviation.management.domain.builder.Builder;
import nl.prbed.hu.aviation.management.domain.factory.PassengerFactory;
import nl.prbed.hu.aviation.management.domain.factory.SeatFactory;

import java.util.List;

@Data
@AllArgsConstructor
@Getter @Setter
public class Aircraft {
    private String code;
    private Type type;
    private List<Seat> seats;
    private Flight current;
    private List<Flight> past;

    public static Builder create() {
        return new AircraftBuilder(new SeatFactory(new PassengerFactory()));
    }
}