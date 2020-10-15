package nl.prbed.hu.aviation.presentation.flightplan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Airport;

@Getter
@RequiredArgsConstructor
public class ResponseFindByCodeFlightplanDto {
    private final String code;
    private final Long duration;
    private final Airport arrival;
    private final Airport destination;
}
