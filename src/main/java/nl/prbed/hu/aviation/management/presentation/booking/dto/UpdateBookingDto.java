package nl.prbed.hu.aviation.management.presentation.booking.dto;

import nl.prbed.hu.aviation.management.domain.SeatType;

import java.util.List;

public class UpdateBookingDto {
    public SeatType seatType;
    public List<CreatePassengerDto> passengers;
}
