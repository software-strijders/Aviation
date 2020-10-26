package nl.prbed.hu.aviation.management.presentation.booking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import nl.prbed.hu.aviation.management.domain.SeatType;

import java.util.List;

@ApiModel(description = "Booking information")
public class CreateBookingDto {
    @ApiModelProperty(notes = "The customer id of the booking")
    public Long customerId;

    @ApiModelProperty(notes = "The flight id of the booking")
    public Long flightId;

    @ApiModelProperty(notes = "The seat type of the booking")
    public SeatType seatType;

    @ApiModelProperty(notes = "The passengers of the booking")
    public List<CreatePassengerDto> passengers;
}
