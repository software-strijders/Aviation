package nl.prbed.hu.aviation.management.data.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringBookingRepository extends JpaRepository<BookingEntity, Long> {
}
