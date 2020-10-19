package nl.prbed.hu.aviation.presentation.flightplan.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Flightplan;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "All flight plans in the database")
public class FindAllFlightplanResponseDto {
    @ApiModelProperty(notes = "the list of flight plans")
    private final List<Flightplan> Flightplans;
}
