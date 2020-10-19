package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Type;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "The aircraft type")
public class CreateTypeResponseDto {
    @ApiModelProperty(notes = "The type")
    private final Type type;
}
