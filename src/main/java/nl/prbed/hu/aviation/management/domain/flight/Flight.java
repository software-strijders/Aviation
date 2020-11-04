package nl.prbed.hu.aviation.management.domain.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.management.domain.aircraft.Seat;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.domain.booking.Booking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        var availableSeats = this.getAvailableSeats();
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

    public Map<SeatType, Integer> getAvailableSeatsMap() {
        var map = new HashMap<SeatType, Integer>();
        for (var seatType : SeatType.values())
            map.put(seatType, 0);

        var availableSeats = this.getAvailableSeats();
        for (var seat : availableSeats) {
            var seatType = seat.getSeatType();
            var currentSeats = map.get(seatType) + 1;
            map.put(seatType, currentSeats);
        }
        return map;
    }

    private List<Seat> getAvailableSeats() {
        if (this.seats == null)
            return new ArrayList<>();

        return this.seats.stream().filter(Seat::doesNotHavePassenger)
                .collect(Collectors.toList());
    }
}
