package nl.prbed.hu.aviation.management.domain.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.Seat;
import nl.prbed.hu.aviation.management.domain.SeatType;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.domain.booking.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Flight {
    private Long id;
    private String code;
    private double priceEconomy;
    private double priceBusiness;
    private double priceFirst;
    private LocalDateTime departureDateTime;
    private List<Booking> bookings;
    private List<Seat> seats;
    private Aircraft aircraft;
    private Flightplan flightplan;

    public boolean areSeatsAvailable(SeatType type, int passengerAmount) {
        var availableSeats = this.seats.stream()
                .filter(Seat::doesNotHavePassenger).collect(Collectors.toList());
        if (availableSeats.size() < passengerAmount)
            // Early return here, because we only want to check the actual seat types
            // when there are enough seats left over
            return false;

        return availableSeats.stream()
                .filter(seat -> seat.getSeatType().equals(type)).count() >= passengerAmount;
    }

    public double getPriceBySeatType(SeatType type) {
        return switch(type) {
            case BUSINESS -> this.priceBusiness;
            case ECONOMY -> this.priceEconomy;
            case FIRST -> this.priceFirst;
        };
    }
}
