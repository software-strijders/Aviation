package nl.prbed.hu.aviation.management.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the aircraft type")
public class CreateTypeDto {
    @ApiModelProperty(notes = "The model name")
    public String modelName;

    @ApiModelProperty(notes = "The manufacturer")
    public String manufacturer;

    @ApiModelProperty(notes = "The total fuel capacity")
    public int fuelCapacity;

    @ApiModelProperty(notes = "The fuel consumption in kilograms per kilometer")
    public int fuelConsumption;

    @ApiModelProperty(notes = "The seats of first class")
    public int numSeatsFirst;

    @ApiModelProperty(notes = "The seats of business class")
    public int numSeatsBusiness;

    @ApiModelProperty(notes = "The seats of economy class")
    public int numSeatsEconomy;
}
