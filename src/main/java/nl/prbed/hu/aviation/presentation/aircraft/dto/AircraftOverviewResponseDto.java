package nl.prbed.hu.aviation.presentation.aircraft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Aircraft;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "All aircraft in the database")
public class AircraftOverviewResponseDto {
    @ApiModelProperty(notes = "List of all aircraft")
    private final List<Aircraft> aircraft;
}
