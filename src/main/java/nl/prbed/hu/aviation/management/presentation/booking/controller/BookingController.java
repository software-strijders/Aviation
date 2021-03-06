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
import nl.prbed.hu.aviation.security.data.UserProfile;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Secured("ROLE_EMPLOYEE")
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());

    private final CreateBookingDtoMapper mapper = CreateBookingDtoMapper.instance;
    private final UpdateBookingDtoMapper updateMapper = UpdateBookingDtoMapper.instance;

    @ApiOperation(value = "Delete a booking")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.bookingService.deleteById(id);
    }

    @ApiOperation(value = "Find all bookings")
    @GetMapping
    public CollectionModel<EntityModel<BookingResponseDto>> findAll() {
        return this.createCollectionModel(this.bookingService.findAll());
    }

    @ApiOperation(value = "Find all bookings from a specific customer")
    @GetMapping("/{id}")
    public CollectionModel<EntityModel<BookingResponseDto>> findByCustomer(@PathVariable Long id) {
        return this.createCollectionModel(this.bookingService.findByCustomer(id));
    }

    @ApiOperation(
            value = "Update a booking",
            notes = "Provide a list of passengers that should replace the current passengers"
    )
    @Secured({"ROLE_CUSTOMER", "ROLE_EMPLOYEE"})
    @PatchMapping("/{id}")
    public EntityModel<BookingResponseDto> update(@PathVariable Long id, @RequestBody UpdateBookingDto dto, Authentication authentication) {
        var booking = this.bookingService.update(id, this.updateMapper.toUpdateBookingStruct(dto), authentication);
        var response = this.createResponseDto(booking);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.UPDATE, id.toString()));
    }

    @ApiOperation(
            value = "Confirm a booking",
            notes = "Provide the customer ID or be logged in as the customer."
    )
    @Secured({"ROLE_CUSTOMER", "ROLE_EMPLOYEE"})
    @PatchMapping("/confirm")
    public EntityModel<BookingResponseDto> confirm(@RequestBody CreateBookingDto dto, Authentication authentication) {
        var booking = this.bookingService.confirmBooking(this.getCustomerId(dto, authentication));
        var response = this.createResponseDto(booking);
        return EntityModel.of(response , hateoasDirector.make(HateoasType.UPDATE, "confirm"));
    }

    @ApiOperation(
            value = "Cancel an unconfirmed booking",
            notes = "Provide the customer ID or be logged in as the customer."
    )
    @Secured({"ROLE_CUSTOMER", "ROLE_EMPLOYEE"})
    @DeleteMapping("/cancel")
    public void cancel(@RequestBody CreateBookingDto dto, Authentication authentication) {
        this.bookingService.cancelBooking(this.getCustomerId(dto, authentication));
    }

    @ApiOperation(
            value = "Create a booking",
            notes = "Provide the details of an booking."
    )
    @Secured({"ROLE_CUSTOMER", "ROLE_EMPLOYEE"})
    @PostMapping
    public EntityModel<BookingResponseDto> create(@RequestBody CreateBookingDto dto, Authentication authentication) {
        var id = this.getCustomerId(dto, authentication);
        var struct = this.mapper.toBookingStruct(dto);
        struct.customerId = id; // Override if the request came from a customer
        var booking = this.bookingService.create(struct);
        var response = this.createResponseDto(booking);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.CREATE));
    }

    private Long getCustomerId(CreateBookingDto dto, Authentication authentication) {
        var user = (UserProfile) authentication.getPrincipal();
        // When the customer is authorised, we want to get the id from the authentication. In any other situation it
        // should be supplied in the dto.
        if (user.getRole().equals("ROLE_CUSTOMER"))
            return user.getId();

        return dto.customerId;
    }

    private BookingResponseDto createResponseDto(Booking booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getPrice(),
                booking.isConfirmed(),
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
