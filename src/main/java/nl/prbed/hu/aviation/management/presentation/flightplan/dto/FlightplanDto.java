package nl.prbed.hu.aviation.management.presentation.flightplan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Details about the flightplan")
public class FlightplanDto {
    @ApiModelProperty(notes = "The code of the flightplan")
    public String code;

    @ApiModelProperty(notes = "The duration of the flightplan")
    public Long duration;

    @ApiModelProperty(notes = "The departure airport")
    public String departure;

    @ApiModelProperty(notes = "The destination airport")
    public String destination;
}
