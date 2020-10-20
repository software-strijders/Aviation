package nl.prbed.hu.aviation.presentation.airport.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Airport;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AirportsResponseDto {
    @ApiModelProperty(notes = "The list of airports")
    private final List<Airport> airports;
}
