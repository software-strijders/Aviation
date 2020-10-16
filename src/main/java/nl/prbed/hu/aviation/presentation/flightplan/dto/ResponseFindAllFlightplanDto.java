package nl.prbed.hu.aviation.presentation.flightplan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Flightplan;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ResponseFindAllFlightplanDto {
    private final List<Flightplan> flightplans;
}
