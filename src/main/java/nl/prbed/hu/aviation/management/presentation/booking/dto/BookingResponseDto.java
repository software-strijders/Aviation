package nl.prbed.hu.aviation.management.presentation.booking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.Passenger;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.domain.flight.Flightplan;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about the booking")
public class BookingResponseDto {
    @ApiModelProperty(notes = "The price of the booking")
    private final double price;

    @ApiModelProperty(notes = "Is the booking confirmed")
    private final boolean confirmed;

    @ApiModelProperty(notes = "The flight code of the booking")
    private final String flightCode;

    @ApiModelProperty(notes = "The aircraft of the booking")
    private final Aircraft aircraft;

    @ApiModelProperty(notes = "The flightplan of the booking")
    private final Flightplan flightplan;

    @ApiModelProperty(notes = "The passengers of the booking")
    private final List<Passenger> passengers;
}
