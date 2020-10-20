package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.Airport;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel("Details about the city")
public class CityResponseDto {
    @ApiModelProperty(notes = "The name of the city")
    private final String name;

    @ApiModelProperty(notes = "The country in which the city is located")
    private final String country;

    @ApiModelProperty(notes = "The list of airports in the city")
    private final List<Airport> airports;
}
