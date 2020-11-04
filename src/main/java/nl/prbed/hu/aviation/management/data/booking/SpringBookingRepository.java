package nl.prbed.hu.aviation.management.data.booking;

import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringBookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByCustomer(CustomerEntity customerEntity);
    Optional<BookingEntity> findBookingEntityByConfirmedAndCustomer(boolean confirmed, CustomerEntity customerEntity);
    void deleteAllByFlight(FlightEntity entity);
}
