package nl.prbed.hu.aviation.management.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String name) {
        super(String.format("City with code: '%s' already exists", name));
    }
}
