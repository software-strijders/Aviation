package nl.prbed.hu.aviation.management.application.struct;

import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;

import java.util.List;

public class UpdateBookingStruct {
    public SeatType seatType;
    public List<PassengerStruct> passengers;
}
