package nl.prbed.hu.aviation.management.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.aircraft.Type;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "The aircraft type")
public class TypeResponseDto {
    @ApiModelProperty(notes = "The type")
    private final Type type;
}
