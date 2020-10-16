package nl.prbed.hu.aviation.presentation.aircraft.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Type;

@RequiredArgsConstructor
@Getter
public class UpdateAircraftResponseDto {
    private final String code;
    private final Type type;
}
