package nl.prbed.hu.aviation.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AirportAlreadyExistsException extends RuntimeException {
    public AirportAlreadyExistsException(String code) {
        super(String.format("Airport with code: '%s' already exists", code));
    }
}
