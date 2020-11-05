package nl.prbed.hu.aviation.management.application.filter;

import ch.qos.logback.classic.boolex.GEventEvaluator;
import nl.prbed.hu.aviation.management.application.exception.SearchFlightDetailsException;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import nl.prbed.hu.aviation.management.domain.flight.Flight;
import org.apache.commons.collections.EnumerationUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class FilterChainFactory {
    public FilterChain create(List<Flight> flights, Map<String, String> searchDetails) {
        var filterChain = new FilterChain(flights, searchDetails);

        if (searchDetails.containsKey("passengers") && searchDetails.containsKey("flightClass")) {
            if (!GenericValidator.isInt(searchDetails.get("passengers")))
                this.detailsNotCorrect(searchDetails.get("passengers"));
            if(!EnumUtils.isValidEnum(SeatType.class, searchDetails.get("flightClass")))
                this.detailsNotCorrect(searchDetails.get("flightClass"));
            filterChain.addFilter(new AvailableSeatsFilter());
        }
        if (searchDetails.containsKey("from")) {
            if (!(searchDetails.get("from").length() == 3))
                this.detailsNotCorrect(searchDetails.get("from"));
            filterChain.addFilter(new DepartureFilter());
        }
        if (searchDetails.containsKey("to")) {
            if (!(searchDetails.get("to").length() == 3))
                this.detailsNotCorrect(searchDetails.get("to"));
            filterChain.addFilter(new DestinationFilter());
        }
        if (searchDetails.containsKey("date")) {
            if (!GenericValidator.isDate(searchDetails.get("date"), "yyyy-MM-dd", true))
                this.detailsNotCorrect(searchDetails.get("date"));
            filterChain.addFilter(new DateFilter());
        }
        return filterChain;
    }

    private void detailsNotCorrect(String value) {
        throw new SearchFlightDetailsException(String.format("value of: %s is not correct", value));
    }
}
