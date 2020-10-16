package nl.prbed.hu.aviation.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CityAlreadyExistsException extends RuntimeException {
    public CityAlreadyExistsException(String name) {
        super(String.format("City with code: '%s' already excists", name));
    }
}
