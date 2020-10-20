package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the airport")
public class CreateAirportDto {
    @ApiModelProperty(notes = "The code of the airport following the IATA format")
    public String code;

    @ApiModelProperty(notes = "The latitude of the airport in signed degrees format")
    public double latitude;

    @ApiModelProperty(notes = "The longitude of the airport in signed degrees format")
    public double longitude;

    @ApiModelProperty(notes = "The name of the city")
    public String cityName;
}
