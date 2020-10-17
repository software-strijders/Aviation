package nl.prbed.hu.aviation.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(String airport) {
        // TODO: All these 'NotFound' exceptions are very similar, this could probably go into one generic exception.
        super(String.format("Could not find Airport with code '%s'", airport));
    }
}
