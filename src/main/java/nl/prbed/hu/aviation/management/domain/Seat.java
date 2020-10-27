package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Seat {
    private SeatType seatType;
    private Passenger passenger;

    public boolean doesNotHavePassenger() {
        return this.passenger == null;
    }
}
