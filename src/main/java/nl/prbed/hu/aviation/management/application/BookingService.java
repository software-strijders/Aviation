package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.*;
import nl.prbed.hu.aviation.management.application.struct.BookingStruct;
import nl.prbed.hu.aviation.management.application.struct.PassengerStruct;
import nl.prbed.hu.aviation.management.application.struct.UpdateBookingStruct;
import nl.prbed.hu.aviation.management.data.booking.BookingEntity;
import nl.prbed.hu.aviation.management.data.booking.PassengerEntity;
import nl.prbed.hu.aviation.management.data.booking.SpringBookingRepository;
import nl.prbed.hu.aviation.management.data.booking.SpringPassengerRepository;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightRepository;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightSeatRepository;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import nl.prbed.hu.aviation.management.domain.booking.Booking;
import nl.prbed.hu.aviation.management.domain.booking.factory.BookingFactory;
import nl.prbed.hu.aviation.management.domain.flight.factory.FlightFactory;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import nl.prbed.hu.aviation.security.data.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {
    private static final String BOOKING_ERROR_MSG = "Could not find booking with id: '%s'";
    private static final String CUSTOMER_ERROR_MSG = "Could not find customer with id: '%s'";
    private static final String FLIGHT_ERROR_MSG = "Could not find flight with id: '%s'";

    private final SpringBookingRepository bookingRepository;
    private final SpringFlightRepository flightRepository;
    private final SpringFlightSeatRepository flightSeatRepository;
    private final SpringPassengerRepository passengerRepository;
    private final SpringUserRepository userRepository;

    private final BookingFactory bookingFactory;
    private final FlightFactory flightFactory;

    public Booking create(BookingStruct bookingStruct) {
        var flightEntity = this.findFlightEntityById(bookingStruct.flightId);
        var flight = this.flightFactory.from(flightEntity);
        var customer = this.findCustomerEntityById(bookingStruct.customerId);
        if (this.hasUnconfirmed(customer))
            throw new AlreadyHasUnconfirmedBookingException(customer.getUsername());
        if (this.customerHasBookingOnFlight(customer, flightEntity))
            throw new AlreadyBookedException(customer.getFirstName(), flight.getCode());
        else if (!flight.areSeatsAvailable(bookingStruct.seatType, bookingStruct.passengers.size()))
            throw new SeatsUnavailableException(bookingStruct.seatType);

        var passengers = bookingStruct.passengers.stream()
                .map(this::findPassengerById).collect(Collectors.toList());
        var entity = this.bookingRepository.save(
                new BookingEntity(
                        flight.getPriceBySeatType(bookingStruct.seatType),
                        false,
                        this.findCustomerEntityById(bookingStruct.customerId),
                        flightEntity,
                        passengers
                )
        );

        this.addPassengersToFlightSeat(passengers, flightEntity, bookingStruct.seatType);
        this.addBookingToCustomer(entity, bookingStruct.customerId);
        return this.bookingFactory.from(entity);
    }

    public Booking confirmBooking(Long customerId) {
        var customer = this.findCustomerEntityById(customerId);
        var booking = this.findUnconfirmed(customer);
        booking.setConfirmed(true);
        this.bookingRepository.save(booking);

        return bookingFactory.from(booking);
    }

    public void cancelBooking(Long customerId) {
        var customer = this.findCustomerEntityById(customerId);
        var booking = this.findUnconfirmed(customer);
        this.deleteById(booking.getId());
    }

    public void deleteById(Long id) {
        var entity = this.bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(BOOKING_ERROR_MSG, id)));
        var customer = entity.getCustomer();
        customer.getBookings().remove(entity);
        this.removeFlightSeatReferences(entity.getFlight().getFlightSeats(), entity.getPassengers());
        this.userRepository.save(customer);
        this.bookingRepository.delete(entity);
    }

    public List<Booking> findAll() {
        return this.bookingFactory.from(this.bookingRepository.findAll());
    }

    public List<Booking> findByCustomer(Long id) {
        var customer = this.findCustomerEntityById(id);
        var entities = this.bookingRepository.findByCustomer(customer);
        return this.bookingFactory.from(entities);
    }

    public Booking update(Long bookingId, UpdateBookingStruct struct, Authentication authentication) {
        var booking = this.bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(BOOKING_ERROR_MSG, bookingId)));
        var user = (UserProfile) authentication.getPrincipal();
        
        if(user.getRole().equals("ROLE_CUSTOMER")) {
            var customer = this.findCustomerEntityById(user.getId());
            if (!customer.equals(booking.getCustomer())) {
                throw new NotYourBookingException("This booking is not yours!");
            }
        }

        this.removeFlightSeatReferences(booking.getFlight().getFlightSeats(), booking.getPassengers());
        booking.setPassengers(
                struct.passengers.stream()
                        .map(this::findPassengerById)
                        .collect(Collectors.toList())
        );
        this.addPassengersToFlightSeat(booking.getPassengers(), booking.getFlight(), struct.seatType);

        return this.bookingFactory.from(this.bookingRepository.save(booking));
    }

    //TODO: Clean up
    public boolean hasUnconfirmed(CustomerEntity customerEntity) {
        try {
            this.findUnconfirmed(customerEntity);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    public BookingEntity findUnconfirmed(CustomerEntity customer) {
        return this.bookingRepository.findBookingEntityByConfirmedAndCustomer(false, customer)
                .orElseThrow(() -> new EntityNotFoundException("No unconfirmed booking found"));
    }

    private void addBookingToCustomer(BookingEntity entity, Long id) {
        var customer = this.findCustomerEntityById(id);
        customer.addBooking(entity);
        this.userRepository.save(customer);
    }

    private void addPassengersToFlightSeat(List<PassengerEntity> passengers, FlightEntity flightEntity, SeatType type) {
        var unoccupiedFlightSeats = flightEntity.getFlightSeats().stream()
                .filter(flightSeatEntity -> flightSeatEntity.getPassenger() == null &&
                        flightSeatEntity.getSeat().getSeatType().equals(type))
                .limit(passengers.size())
                .collect(Collectors.toList());

        for (int i = 0; i < unoccupiedFlightSeats.size(); i++)
            unoccupiedFlightSeats.get(i).setPassenger(passengers.get(i));
        this.flightSeatRepository.saveAll(unoccupiedFlightSeats);
    }

    private boolean customerHasBookingOnFlight(CustomerEntity customer, FlightEntity flight) {
        var bookings = customer.getBookings();
        return bookings.size() != 0 && bookings.stream()
                .map(BookingEntity::getFlight)
                .anyMatch(flightEntity -> flightEntity.getCode().equals(flight.getCode()));
    }

    private PassengerEntity createPassenger(PassengerStruct passenger) {
        return this.passengerRepository.save(new PassengerEntity(
                passenger.firstName,
                passenger.lastName,
                passenger.birthDate,
                passenger.nationality,
                passenger.email,
                // This will be set later:
                null
        ));
    }

    private PassengerEntity findPassengerById(PassengerStruct struct) {
        if (struct.id == null)
            return this.createPassenger(struct);

        return this.passengerRepository.findById(struct.id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CUSTOMER_ERROR_MSG, struct.id)));
    }

    private CustomerEntity findCustomerEntityById(Long id) {
        return this.map(this.userRepository.findByIdAndCustomer(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CUSTOMER_ERROR_MSG, id))));
    }

    private FlightEntity findFlightEntityById(Long id) {
        return this.flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(FLIGHT_ERROR_MSG, id)));
    }

    private CustomerEntity map(User user) {
        return (CustomerEntity) user;
    }

    private void removeFlightSeatReferences(List<FlightSeatEntity> flightSeats, List<PassengerEntity> passengers) {
        for (var flightSeat : flightSeats) {
            if (flightSeat.getPassenger() == null)
                continue;

            var passengerId = flightSeat.getPassenger().getId();
            for (var passenger : passengers)
                if (passenger.getId().equals(passengerId))
                    flightSeat.setPassenger(null);
        }
    }
}
