package nl.prbed.hu.aviation.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FlightplanNotFoundException extends RuntimeException{
    public FlightplanNotFoundException(String code) { super(String.format("Could not find Flightplan by code '%s'", code)); }
}
