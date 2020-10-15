package nl.prbed.hu.aviation.presentation.aircraft.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Type;

@Getter
@RequiredArgsConstructor
public class CreateTypeResponseDto {
    private final Type type;
}
