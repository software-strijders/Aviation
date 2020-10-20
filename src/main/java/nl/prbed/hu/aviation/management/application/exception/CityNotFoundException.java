package nl.prbed.hu.aviation.management.application.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String cityName) {
        super(String.format("Could not find City with the name '%s'", cityName));
    }
}
