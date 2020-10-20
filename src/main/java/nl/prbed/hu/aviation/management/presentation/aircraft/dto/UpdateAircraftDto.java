package nl.prbed.hu.aviation.management.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "Details about the to be updated aircraft")
public class UpdateAircraftDto {
    @NotBlank
    @ApiModelProperty(notes = "The model name of the type")
    public String modelName;

    @NotBlank
    @ApiModelProperty(notes = "The code of the aircraft. Something like: KLM0001")
    public String code;
}
