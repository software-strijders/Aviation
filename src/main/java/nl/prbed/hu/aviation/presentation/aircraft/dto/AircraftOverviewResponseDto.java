package nl.prbed.hu.aviation.presentation.aircraft.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Aircraft;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AircraftOverviewResponseDto {
    private final List<Aircraft> aircraft;
}
