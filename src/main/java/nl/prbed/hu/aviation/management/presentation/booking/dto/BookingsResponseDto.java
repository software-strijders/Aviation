package nl.prbed.hu.aviation.management.presentation.booking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.booking.Booking;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "All bookings")
public class BookingsResponseDto {
    @ApiModelProperty(notes = "The list of bookings")
    private final List<Booking> bookings;
}
