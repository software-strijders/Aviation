package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Type;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "The aircraft")
public class CreateAircraftResponseDto {
    @ApiModelProperty(notes = "The aircraft's code")
    private final String code;
    @ApiModelProperty(notes = "The aircraft's model name")
    private final Type modelName;
}
