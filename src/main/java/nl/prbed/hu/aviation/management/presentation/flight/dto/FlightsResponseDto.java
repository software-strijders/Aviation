package nl.prbed.hu.aviation.management.presentation.flight.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.domain.flight.Flight;

import java.util.List;

@AllArgsConstructor
@ApiModel(description = "All flights")
public class FlightsResponseDto {
    @ApiModelProperty("List of all flights")
    public List<Flight> flights;
}
