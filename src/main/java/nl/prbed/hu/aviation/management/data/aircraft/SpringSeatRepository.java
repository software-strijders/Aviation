package nl.prbed.hu.aviation.management.data.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSeatRepository extends JpaRepository<SeatEntity, Long> {
}
