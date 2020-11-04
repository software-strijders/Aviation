package nl.prbed.hu.aviation.management.application.filters;

import lombok.NoArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.SearchFlightDetailsException;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class DateFilter implements Filter {
    @Override
    public List<Flight> filter(List<Flight> flights, Map<String, String> searchDetails) {
        var date = LocalDate.parse(
                    searchDetails.get("date"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH)
        );

        if (date.isBefore(LocalDate.now()))
            throw new SearchFlightDetailsException("Date is in the past");

        return flights.stream().filter(f ->
                f.getDepartureDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}
