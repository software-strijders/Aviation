package nl.prbed.hu.aviation.management.presentation.flight.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about the flight")
public class FlightResponseDto {

    @ApiModelProperty(notes = "The code of the flight")
    private final String code;

    @ApiModelProperty(notes = "The price of the flight in economy class")
    private final double priceEconomy;

    @ApiModelProperty(notes = "The price of the flight in business class")
    private final double priceBusiness;

    @ApiModelProperty(notes = "The price of the flight in first class")
    private final double priceFirst;

    @ApiModelProperty(notes = "The code of the aircraft")
    private final String aircraftCode;

    @ApiModelProperty(notes = "The code of the flightplan")
    private final String flightPlanCode;


}
