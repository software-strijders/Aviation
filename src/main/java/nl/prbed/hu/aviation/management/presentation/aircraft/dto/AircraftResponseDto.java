package nl.prbed.hu.aviation.management.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "The aircraft")
public class AircraftResponseDto {
    @ApiModelProperty(notes = "The code of the aircraft. Something like: KLM0001")
    private final String code;

    @ApiModelProperty(notes = "The model name of the type")
    private final String modelName;
}
