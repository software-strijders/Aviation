package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(description = "All aircraft codes")
public class AircraftListDto {
    @ApiModelProperty(notes = "List of aircraft codes")
    @NotNull
    public List<String> codes;
}
