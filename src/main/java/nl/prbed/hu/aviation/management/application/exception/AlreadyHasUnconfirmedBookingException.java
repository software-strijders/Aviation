package nl.prbed.hu.aviation.management.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyHasUnconfirmedBookingException extends RuntimeException {
    public AlreadyHasUnconfirmedBookingException(String customerName) {
        super(String.format("Customer: '%s' already has an unconfirmed booking", customerName));
    }
}
