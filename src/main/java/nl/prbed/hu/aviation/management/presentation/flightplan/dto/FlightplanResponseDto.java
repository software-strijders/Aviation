package nl.prbed.hu.aviation.management.presentation.flightplan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.Airport;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about the flightplan")
public class FlightplanResponseDto {
    @ApiModelProperty(notes = "The code of the flightplan")
    private final String code;

    @ApiModelProperty(notes = "The duration of the flightplan")
    private final Long duration;

    @ApiModelProperty(notes = "The departure airport")
    private final Airport departure;

    @ApiModelProperty(notes = "The destination airport")
    private final Airport destination;
}
