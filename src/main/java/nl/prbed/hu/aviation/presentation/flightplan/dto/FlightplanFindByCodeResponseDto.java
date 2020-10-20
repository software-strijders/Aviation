package nl.prbed.hu.aviation.presentation.flightplan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Airport;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about the flightplan")
public class FlightplanFindByCodeResponseDto {
    @ApiModelProperty(notes = "The code of the flightplan")
    private final String code;
    @ApiModelProperty(notes = "The duration of the flightplan")
    private final Long duration;
    @ApiModelProperty(notes = "The arrival airport")
    private final Airport arrival;
    @ApiModelProperty(notes = "The destination airport")
    private final Airport destination;
}
