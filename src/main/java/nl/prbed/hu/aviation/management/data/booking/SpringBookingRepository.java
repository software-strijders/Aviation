package nl.prbed.hu.aviation.management.data.booking;

import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringBookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByCustomer(CustomerEntity customerEntity);
}
