package nl.prbed.hu.aviation.management.presentation.flight.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about the flight")
public class FlightResponseDto {
    @ApiModelProperty(notes = "The id of the flight")
    private final Long id; // TODO: We should refactor entities that shouldn't have an id (iteration 3)

    @ApiModelProperty(notes = "The code of the flight")
    private final String code;

    @ApiModelProperty(notes = "The price of the flight in economy class")
    private final double priceEconomy;

    @ApiModelProperty(notes = "The price of the flight in business class")
    private final double priceBusiness;

    @ApiModelProperty(notes = "The price of the flight in first class")
    private final double priceFirst;

    @ApiModelProperty(notes = "The avaible seats of the flight grouped by class")
    private final Map<SeatType, Integer> availableSeats;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @ApiModelProperty(notes = "'The departure of the flight in date and time")
    private final LocalDateTime departureDateTime;

    @ApiModelProperty(notes = "The code of the aircraft")
    private final String aircraftCode;

    @ApiModelProperty(notes = "The code of the flightplan")
    private final String flightPlanCode;
}
