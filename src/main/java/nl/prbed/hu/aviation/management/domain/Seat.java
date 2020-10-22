package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Seat {
    private SeatType seatType;
    private Passenger passenger;
}
