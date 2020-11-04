package nl.prbed.hu.aviation.management.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AircraftInUseException extends RuntimeException {
    public AircraftInUseException(String aircraftCode, LocalDateTime departure, LocalDateTime arrival) {
        super(String.format("Aircraft with code: %s is in use between %s and %s", aircraftCode, departure, arrival));
    }
}
