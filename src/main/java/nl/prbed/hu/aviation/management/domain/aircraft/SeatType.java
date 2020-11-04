package nl.prbed.hu.aviation.management.domain.aircraft;

import java.util.stream.Stream;

public enum SeatType {
    ECONOMY("ECONOMY"),
    BUSINESS("BUSINESS"),
    FIRST("FIRST");

    private final String seat;

    SeatType(String seat) {
        this.seat = seat;
    }

    public static SeatType fromString(String seat) {
        return Stream.of(SeatType.values()).filter(seatType -> seatType.seat.equals(seat))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid class"));
    }
}
