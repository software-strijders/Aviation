package nl.prbed.hu.aviation.management.domain.aircraft.builder;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.domain.aircraft.Seat;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import nl.prbed.hu.aviation.management.domain.aircraft.Type;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.SeatFactory;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

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
    public Builder addSeats(int num, SeatType type) {
        this.seats.addAll(seatFactory.from(num, type));
        return this;
    }

    @Override
    public Builder seats(List<Seat> seats) {
        this.seats = seats;
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
