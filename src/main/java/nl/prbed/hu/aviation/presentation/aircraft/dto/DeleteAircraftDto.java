package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the aircraft to delete")
public class DeleteAircraftDto {
    @ApiModelProperty(notes = "The code of the aircraft. Something like KLM0001")
    public String code;
}
