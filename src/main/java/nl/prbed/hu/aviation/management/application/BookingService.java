package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.AlreadyBookedException;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.application.exception.SeatsUnavailableException;
import nl.prbed.hu.aviation.management.application.struct.BookingStruct;
import nl.prbed.hu.aviation.management.application.struct.PassengerStruct;
import nl.prbed.hu.aviation.management.data.aircraft.SpringSeatRepository;
import nl.prbed.hu.aviation.management.data.booking.BookingEntity;
import nl.prbed.hu.aviation.management.data.booking.PassengerEntity;
import nl.prbed.hu.aviation.management.data.booking.SpringBookingRepository;
import nl.prbed.hu.aviation.management.data.booking.SpringPassengerRepository;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightRepository;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightSeatRepository;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.management.domain.SeatType;
import nl.prbed.hu.aviation.management.domain.booking.Booking;
import nl.prbed.hu.aviation.management.domain.booking.factory.BookingFactory;
import nl.prbed.hu.aviation.management.domain.factory.CustomerFactory;
import nl.prbed.hu.aviation.management.domain.flight.factory.FlightFactory;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {
    private static final String CUSTOMER_ERROR_MSG = "Could not find customer with id '%s'";
    private static final String FLIGHT_ERROR_MSG = "Could not find flight with id '%s'";

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
        if (this.customerHasBookingOnFlight(customer, flightEntity))
            throw new AlreadyBookedException(customer.getFirstName(), flight.getCode());
        else if (!flight.areSeatsAvailable(bookingStruct.seatType, bookingStruct.passengers.size()))
            throw new SeatsUnavailableException(bookingStruct.seatType);

        var passengers = bookingStruct.passengers.stream()
                .map(this::findPassengerByFirstName).collect(Collectors.toList());
        var bookingEntity = this.bookingRepository.save(
                new BookingEntity(
                        flight.getPriceBySeatType(bookingStruct.seatType),
                        this.findCustomerEntityById(bookingStruct.customerId),
                        flightEntity,
                        passengers
                )
        );

        this.addPassengersToFlightSeat(passengers, flightEntity, bookingStruct.seatType);
        this.addBookingToCustomer(bookingEntity, bookingStruct.customerId);
        return this.bookingFactory.from(bookingEntity);
    }

    private boolean customerHasBookingOnFlight(CustomerEntity customer, FlightEntity flight) {
        var bookings = customer.getBookings();
        return bookings.size() != 0 && bookings.stream()
                .map(BookingEntity::getFlight)
                .anyMatch(flightEntity -> flightEntity.getCode().equals(flight.getCode()));
    }

    private PassengerEntity findPassengerByFirstName(PassengerStruct passenger) {
        // FIXME: What if two passengers have the same name?
        var passengerEntity = this.passengerRepository.findByFirstName(passenger.firstName);
        if (passengerEntity.isEmpty())
            return this.createPassenger(passenger);
        return passengerEntity.get();
    }

    private PassengerEntity createPassenger(PassengerStruct passenger) {
        return this.passengerRepository.save(new PassengerEntity(
                passenger.firstName,
                passenger.lastName,
                passenger.birthDate,
                passenger.nationality,
                // This will be set later:
                null
        ));
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

    private void addPassengersToFlightSeat(List<PassengerEntity> passengers, FlightEntity flightEntity, SeatType type) {
        var unnocupiedFlightSeats = flightEntity.getFlightSeats().stream()
                .filter(flightSeatEntity -> flightSeatEntity.getPassenger() == null &&
                        flightSeatEntity.getSeat().getSeatType().equals(type))
                .limit(passengers.size())
                .collect(Collectors.toList());

        for (int i = 0; i < unnocupiedFlightSeats.size(); i++)
            unnocupiedFlightSeats.get(i).setPassenger(passengers.get(i));
        this.flightSeatRepository.saveAll(unnocupiedFlightSeats);
    }

    private void addBookingToCustomer(BookingEntity entity, Long id) {
        var customer = this.findCustomerEntityById(id);
        customer.addBooking(entity);
        this.userRepository.save(customer);
    }
}
