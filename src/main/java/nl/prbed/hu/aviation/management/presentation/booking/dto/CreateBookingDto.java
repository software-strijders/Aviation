package nl.prbed.hu.aviation.management.presentation.booking.dto;

import nl.prbed.hu.aviation.management.domain.SeatType;

import java.util.List;

public class CreateBookingDto {
    public Long customerId;
    public Long flightId;
    public SeatType seatType; // Could also be a string
    public List<CreatePassengerDto> passengers;
}
