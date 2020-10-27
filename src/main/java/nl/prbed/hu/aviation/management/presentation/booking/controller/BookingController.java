package nl.prbed.hu.aviation.management.presentation.booking.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.BookingService;
import nl.prbed.hu.aviation.management.domain.booking.Booking;
import nl.prbed.hu.aviation.management.presentation.booking.dto.BookingResponseDto;
import nl.prbed.hu.aviation.management.presentation.booking.dto.CreateBookingDto;
import nl.prbed.hu.aviation.management.presentation.booking.mapper.CreateBookingDtoMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_EMPLOYEE")
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final CreateBookingDtoMapper mapper = CreateBookingDtoMapper.instance;
    private final BookingService bookingService;

    @ApiOperation(
            value = "Create a booking",
            notes = "Provide the details of an booking."
    )
    @PostMapping
    public BookingResponseDto create(@RequestBody CreateBookingDto dto) {
        var booking = this.bookingService.create(this.mapper.toBookingStruct(dto));
        return this.createResponseDto(booking);
    }

    private BookingResponseDto createResponseDto(Booking booking) {
        return new BookingResponseDto(
                booking.getPrice(),
                booking.getFlight().getCode(),
                booking.getFlight().getAircraft(),
                booking.getFlight().getFlightplan(),
                booking.getPassengers()
        );
    }
}
