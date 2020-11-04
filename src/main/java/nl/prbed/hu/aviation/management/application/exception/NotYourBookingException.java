package nl.prbed.hu.aviation.management.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
public class NotYourBookingException extends RuntimeException {
    public NotYourBookingException(String message) {
        super(message);
    }
}
