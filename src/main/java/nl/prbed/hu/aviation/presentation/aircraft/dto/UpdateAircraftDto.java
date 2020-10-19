package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "Details about the to be updated aircraft")
public class UpdateAircraftDto {
    @NotBlank
    @ApiModelProperty(notes = "The model name of the aircraft")
    public String modelName;

    @NotBlank
    @ApiModelProperty(notes = "The code of the aircraft")
    public String code;
}
