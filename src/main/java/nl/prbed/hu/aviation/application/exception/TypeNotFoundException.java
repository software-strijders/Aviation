package nl.prbed.hu.aviation.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String modelName) {
        super(String.format("Could not find model by name: '%s'", modelName));
    }
}
