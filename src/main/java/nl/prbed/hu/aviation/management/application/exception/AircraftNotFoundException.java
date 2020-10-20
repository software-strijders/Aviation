package nl.prbed.hu.aviation.management.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AircraftNotFoundException extends RuntimeException {
    public AircraftNotFoundException(String code) {
        super(String.format("Could not find model by code: '%s'", code));
    }
}
