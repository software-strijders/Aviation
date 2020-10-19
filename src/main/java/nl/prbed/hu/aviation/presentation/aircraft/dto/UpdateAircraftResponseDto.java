package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Type;

@RequiredArgsConstructor
@Getter
@ApiModel(description = "Details about the updated aircraft")
public class UpdateAircraftResponseDto {
    @ApiModelProperty(notes = "The code of the aircraft")
    private final String code;
    @ApiModelProperty(notes = "The type of the aircraft")
    private final Type type;
}
