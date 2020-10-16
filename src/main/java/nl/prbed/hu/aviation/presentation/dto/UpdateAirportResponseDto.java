package nl.prbed.hu.aviation.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.City;

@Getter
@RequiredArgsConstructor
public class UpdateAirportResponseDto {
    private final String code;
    private final float latitude;
    private final float longitude;
    private final City city;
}
