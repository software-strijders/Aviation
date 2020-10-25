package nl.prbed.hu.aviation.management.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlreadyBookedException extends RuntimeException {
    public AlreadyBookedException(String customerName, String flightCode) {
        super(String.format("The customer '%s' already has a booking on flight '%s'", customerName, flightCode));
    }
}
