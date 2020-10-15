package nl.prbed.hu.aviation.presentation.flightplan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseFlightplanDto {
    private final String code;
    private final Long duration;
}
