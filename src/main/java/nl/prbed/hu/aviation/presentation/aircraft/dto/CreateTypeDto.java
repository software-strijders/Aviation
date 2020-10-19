package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the aircraft type")
public class CreateTypeDto {
    @ApiModelProperty(notes = "The type's model name")
    public String modelName;
    @ApiModelProperty(notes = "The type's manufacturer")
    public String manufacturer;
    @ApiModelProperty(notes = "The type's total fuel capacity")
    public int fuelCapacity;
    @ApiModelProperty(notes = "The type's fuel consumption in kilograms per kilometer")
    public int fuelConsumption;
    @ApiModelProperty(notes = "The type's seats of first class")
    public int numSeatsFirst;
    @ApiModelProperty(notes = "The type's seats of business class")
    public int numSeatsBusiness;
    @ApiModelProperty(notes = "The type's seats of economy class")
    public int numSeatsEconomy;
}
