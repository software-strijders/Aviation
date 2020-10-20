package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the city")
public class CreateCityDto {
    @ApiModelProperty(notes = "The name of the city")
    public String name;

    @ApiModelProperty(notes = "The name of the country the city is located in")
    public String country;
}
