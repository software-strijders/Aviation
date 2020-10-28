package nl.prbed.hu.aviation.management.presentation.booking.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.BookingService;
import nl.prbed.hu.aviation.management.domain.booking.Booking;
import nl.prbed.hu.aviation.management.presentation.booking.dto.BookingResponseDto;
import nl.prbed.hu.aviation.management.presentation.booking.dto.CreateBookingDto;
import nl.prbed.hu.aviation.management.presentation.booking.dto.UpdateBookingDto;
import nl.prbed.hu.aviation.management.presentation.booking.mapper.CreateBookingDtoMapper;
import nl.prbed.hu.aviation.management.presentation.booking.mapper.UpdateBookingDtoMapper;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasBuilder;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasDirector;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Secured("ROLE_EMPLOYEE")
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService service;
    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());

    private final CreateBookingDtoMapper mapper = CreateBookingDtoMapper.instance;
    private final UpdateBookingDtoMapper updateMapper = UpdateBookingDtoMapper.instance;

    @ApiOperation(value = "Delete a booking")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.service.deleteById(id);
    }

    @ApiOperation(value = "Find all bookings")
    @GetMapping
    public CollectionModel<EntityModel<BookingResponseDto>> findAll() {
        return this.createCollectionModel(this.service.findAll());
    }

    @ApiOperation(value = "Find all bookings from a specific customer")
    @GetMapping("/{id}")
    public CollectionModel<EntityModel<BookingResponseDto>> findByCustomer(@PathVariable Long id) {
        return this.createCollectionModel(this.service.findByCustomer(id));
    }

    @ApiOperation(
            value = "Update a booking",
            notes = "Provide a list of passengers that should replace the current passengers"
    )
    @PatchMapping("/{id}")
    public EntityModel<BookingResponseDto> update(@PathVariable Long id, @RequestBody UpdateBookingDto dto) {
        var booking = this.service.update(id, this.updateMapper.toUpdateBookingStruct(dto));
        var response = this.createResponseDto(booking);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.UPDATE, id.toString()));
    }

    @ApiOperation(
            value = "Create a booking",
            notes = "Provide the details of an booking."
    )
    @PostMapping
    public EntityModel<BookingResponseDto> create(@RequestBody CreateBookingDto dto) {
        var booking = this.service.create(this.mapper.toBookingStruct(dto));
        var response = this.createResponseDto(booking);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.NONE));
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

    private CollectionModel<EntityModel<BookingResponseDto>> createCollectionModel(List<Booking> bookings) {
        var response = bookings.stream()
                .map(this::createResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getFlightCode())))
                .collect(Collectors.toList());
        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL));
    }
}
