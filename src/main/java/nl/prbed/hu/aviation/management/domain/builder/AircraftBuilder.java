package nl.prbed.hu.aviation.management.domain.builder;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.*;
import nl.prbed.hu.aviation.management.domain.factory.SeatFactory;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AircraftBuilder implements Builder {
    //TODO Maybe add dependency injection
    private final SeatFactory seatFactory;

    private String code;
    private List<Seat> seats = new ArrayList<>();
    private Type type;
    private Flight flight;


    @Override
    public Builder code(String code) {
        this.code = code;
        return this;
    }

    @Override
    public Builder firstSeats(int num) {
        this.seats.addAll(seatFactory.from(num, SeatType.FIRST));
        return this;
    }

    @Override
    public Builder businessSeats(int num) {
        this.seats.addAll(seatFactory.from(num, SeatType.BUSINESS));
        return this;
    }

    @Override
    public Builder economySeats(int num) {
        this.seats.addAll(seatFactory.from(num, SeatType.ECONOMY));
        return this;
    }

    @Override
    public Builder type(Type type) {
        this.type = type;
        return this;
    }

    @Override
    public Builder flight(Flight flight) {
        this.flight = flight;
        return this;
    }

    @Override
    public Aircraft build() {
        return new Aircraft(this.code, this.type, this.seats, this.flight, new ArrayList<>());
    }
}
