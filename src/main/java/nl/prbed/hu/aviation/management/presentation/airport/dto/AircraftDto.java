package nl.prbed.hu.aviation.management.presentation.airport.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Aircraft code")
public class AircraftDto {
    @ApiModelProperty(notes = "Code of aircraft")
    public String aircraftcode;
}
