package nl.prbed.hu.aviation.management.application.exception;

import nl.prbed.hu.aviation.management.domain.SeatType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatsUnavailableException extends RuntimeException {
    public SeatsUnavailableException(SeatType type) {
        super(String.format("There are no seats avaiable for the class '%s'", type));
    }
}
