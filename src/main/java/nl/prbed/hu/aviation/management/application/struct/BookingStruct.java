package nl.prbed.hu.aviation.management.application.struct;

import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;

import java.util.List;

public class BookingStruct {
    public Long customerId;
    public Long flightId;
    public SeatType seatType;
    public List<PassengerStruct> passengers;
}
