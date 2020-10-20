package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(description = "Details about the airport")
public class CreateAirportDto {
    @NotBlank
    @ApiModelProperty(notes = "The code of the airport following the IATA format")
    public String code;

    @NotNull
    @ApiModelProperty(notes = "The latitude of the airport in signed degrees format")
    public double latitude;

    @NotNull
    @ApiModelProperty(notes = "The longitude of the airport in signed degrees format")
    public double longitude;

    @NotBlank
    @ApiModelProperty(notes = "The name of the city")
    public String cityName;

    @ApiModelProperty(notes = "The list of aircraftCodes to add")
    public List<String> aircraftCodes;
}
