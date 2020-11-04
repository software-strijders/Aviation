package nl.prbed.hu.aviation.management.domain.aircraft;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.aircraft.builder.AircraftBuilder;
import nl.prbed.hu.aviation.management.domain.aircraft.builder.Builder;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.SeatFactory;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.List;

@Data
@AllArgsConstructor
public class Aircraft {
    private String code;
    private Type type;

    // TODO: These are not doing anyting yet:
    @JsonIgnore
    private List<Seat> seats;
    private Flight current;
    private List<Flight> past;

    public static Builder create() {
        return new AircraftBuilder(new SeatFactory());
    }
}
