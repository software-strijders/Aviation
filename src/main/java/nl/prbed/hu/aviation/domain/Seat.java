package nl.prbed.hu.aviation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Seat {
    private SeatType seatType;
    private List<Passenger> passengers; //not sure why this needs to be a List?
}
