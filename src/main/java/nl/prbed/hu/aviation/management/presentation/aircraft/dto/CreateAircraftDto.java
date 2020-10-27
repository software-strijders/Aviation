package nl.prbed.hu.aviation.management.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the aircraft")
public class CreateAircraftDto {
    @ApiModelProperty(notes = "The model name of the aircraft")
    public String modelName;

    @ApiModelProperty(notes = "The code of the aircraft. Something like: KLM0001")
    public String code;

    @ApiModelProperty(notes = "The number of first class seats inside the aircraft")
    public int seatsFirst;

    @ApiModelProperty(notes = "The number of business class seats inside the aircraft")
    public int seatsBusiness;

    @ApiModelProperty(notes = "The number of economy class seats inside the aircraft")
    public int seatsEconomy;

    @ApiModelProperty(notes = "The code of the airport where the aircraft is stationed")
    public String airportCode;
}
