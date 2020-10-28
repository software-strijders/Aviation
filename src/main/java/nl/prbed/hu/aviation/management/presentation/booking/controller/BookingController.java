package nl.prbed.hu.aviation.management.presentation.booking.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.BookingService;
import nl.prbed.hu.aviation.management.domain.booking.Booking;
import nl.prbed.hu.aviation.management.presentation.booking.dto.BookingResponseDto;
import nl.prbed.hu.aviation.management.presentation.booking.dto.CreateBookingDto;
import nl.prbed.hu.aviation.management.presentation.booking.mapper.CreateBookingDtoMapper;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasBuilder;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasDirector;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import org.springframework.hateoas.EntityModel;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured("ROLE_EMPLOYEE")
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final CreateBookingDtoMapper mapper = CreateBookingDtoMapper.instance;
    private final BookingService service;
    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());

    @ApiOperation(
            value = "Create a booking",
            notes = "Provide the details of an booking."
    )
    @PostMapping
    public EntityModel<BookingResponseDto> create(@RequestBody CreateBookingDto dto) {
        var booking = this.service.create(this.mapper.toBookingStruct(dto));
        var response = createResponseDto(booking);
        return EntityModel.of(response, hateoasDirector.make(HateoasType.NONE, ""));
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

    @ApiOperation(value = "Delete a booking")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.service.deleteById(id);
    }
}
