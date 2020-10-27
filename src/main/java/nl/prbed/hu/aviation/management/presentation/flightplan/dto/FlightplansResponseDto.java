package nl.prbed.hu.aviation.management.presentation.flightplan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.flight.Flightplan;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "All flightplans")
public class FlightplansResponseDto {
    @ApiModelProperty(notes = "The list of flightplans")
    private final List<Flightplan> Flightplans;
}
