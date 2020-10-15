package nl.prbed.hu.aviation.presentation.aircraft.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Type;

@Getter
@RequiredArgsConstructor
public class CreateAircraftResponseDto {
    private final String code;
    private final Type modelName;
}
