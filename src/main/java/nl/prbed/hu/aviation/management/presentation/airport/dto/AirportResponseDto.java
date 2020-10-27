package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.City;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about the airport")
public class AirportResponseDto {
    @ApiModelProperty(notes = "The code of the airport")
    private final String code;

    @ApiModelProperty(notes = "The latitude of the airport in signed degrees format")
    private final double latitude;

    @ApiModelProperty(notes = "The longitude of the airport in signed degrees format")
    private final double longitude;

    @ApiModelProperty(notes = "The city where the airport is located in")
    private final City cityName;

    @ApiModelProperty(notes = "The list of all aircraft from the airport")
    private final List<Aircraft> aircraft;
}
