package nl.prbed.hu.aviation.management.domain.aircraft;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.booking.Passenger;

@Data
@AllArgsConstructor
public class Seat {
    private SeatType seatType;
    private Passenger passenger;
    private int seatNumber;

    public boolean doesNotHavePassenger() {
        return this.passenger == null;
    }
}
