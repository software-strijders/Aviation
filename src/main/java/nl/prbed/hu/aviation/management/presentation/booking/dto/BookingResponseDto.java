package nl.prbed.hu.aviation.management.presentation.booking.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.Passenger;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.domain.flight.Flightplan;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BookingResponseDto {
    private final double price;
    private final String flightCode;
    private final Aircraft aircraft;
    private final Flightplan flightplan;
    private final List<Passenger> passengers;
}
