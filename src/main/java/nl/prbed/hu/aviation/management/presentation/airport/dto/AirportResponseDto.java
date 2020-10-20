package nl.prbed.hu.aviation.management.presentation.airport.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about the airport")
public class AirportResponseDto {
    @ApiModelProperty(notes = "The code of the airport")
    private final String code;

    @ApiModelProperty(notes = "The latitude of the airport in signed degrees format")
    private final double latitude;

    @ApiModelProperty(notes = "The longitude of the airport in signed degrees format")
    private final double longitude;

    @ApiModelProperty(notes = "The longitude of the airport in signed degrees format")
    private final String cityName;
}
