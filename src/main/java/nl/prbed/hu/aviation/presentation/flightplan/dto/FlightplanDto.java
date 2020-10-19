package nl.prbed.hu.aviation.presentation.flightplan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Details about the flight plan")
public class FlightplanDto {
    @ApiModelProperty(notes = "The code of the flight plan")
    public String code;
    @ApiModelProperty(notes = "The duration of the flight plan")
    public Long duration;
    public String arrival;
    public String destination;
}
