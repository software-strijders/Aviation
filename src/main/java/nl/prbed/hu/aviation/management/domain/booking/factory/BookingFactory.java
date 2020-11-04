package nl.prbed.hu.aviation.management.domain.booking.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.booking.BookingEntity;
import nl.prbed.hu.aviation.management.domain.booking.Booking;
import nl.prbed.hu.aviation.management.domain.user.factory.CustomerFactory;
import nl.prbed.hu.aviation.management.domain.flight.factory.FlightFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingFactory {
    private final CustomerFactory customerfactory;
    private final FlightFactory flightFactory;
    private final PassengerFactory passengerFactory;

    public Booking from(BookingEntity entity) {
        return new Booking(
                entity.getId(),
                entity.getPrice(),
                entity.getConfirmed(),
                this.customerfactory.from(entity.getCustomer()),
                this.flightFactory.from(entity.getFlight()),
                this.passengerFactory.from(entity.getPassengers())
        );
    }

    public List<Booking> from(List<BookingEntity> bookings) {
        return bookings.stream().map(this::from).collect(Collectors.toList());
    }
}
