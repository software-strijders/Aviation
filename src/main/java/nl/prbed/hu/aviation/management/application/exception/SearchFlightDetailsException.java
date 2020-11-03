package nl.prbed.hu.aviation.management.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SearchFlightDetailsException extends RuntimeException {
    public SearchFlightDetailsException(String message) {
        super(message);
    }
}
