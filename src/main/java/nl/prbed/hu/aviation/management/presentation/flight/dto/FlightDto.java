package nl.prbed.hu.aviation.management.presentation.flight.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the flight")
public class FlightDto {

    @ApiModelProperty(notes = "The code of the flight")
    public String code;

    @ApiModelProperty(notes = "The price of the flight in economy class")
    public double priceEconomy;

    @ApiModelProperty(notes = "The price of the flight in business class")
    public double priceBusiness;

    @ApiModelProperty(notes = "The price of the flight in first class")
    public double priceFirst;

    @ApiModelProperty(notes = "The code of the aircraft")
    public String aircraftCode;

    @ApiModelProperty(notes = "The code of the flightplan")
    public String flightPlanCode;
}
