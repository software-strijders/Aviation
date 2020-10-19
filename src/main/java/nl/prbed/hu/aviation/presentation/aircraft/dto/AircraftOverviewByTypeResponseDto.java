package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Aircraft;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "All aircraft of a certain aircraft type")
public class AircraftOverviewByTypeResponseDto {
    @ApiModelProperty(notes = "The aircraft type")
    private final String type;
    @ApiModelProperty(notes = "The list of aircraft")
    private final List<Aircraft> aircraft;
}
