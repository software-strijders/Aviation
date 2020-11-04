package nl.prbed.hu.aviation.management.application.exception;

import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatsUnavailableException extends RuntimeException {
    public SeatsUnavailableException(SeatType type) {
        super(String.format("There are no seats available for the class: '%s'", type));
    }
}
