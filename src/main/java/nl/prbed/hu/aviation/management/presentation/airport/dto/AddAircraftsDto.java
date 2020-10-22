package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "All aircrafts that should be added")
public class AddAircraftsDto {
    @ApiModelProperty(notes = "List of aircraft codes")
    public List<String> aircraftCodes;
}
