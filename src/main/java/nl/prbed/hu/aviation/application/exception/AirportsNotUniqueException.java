package nl.prbed.hu.aviation.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AirportsNotUniqueException extends RuntimeException {
    public AirportsNotUniqueException() {
        super("Arrival and destination cannot be the same");
    }
}
