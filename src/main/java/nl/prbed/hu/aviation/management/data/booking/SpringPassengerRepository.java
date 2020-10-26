package nl.prbed.hu.aviation.management.data.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringPassengerRepository extends JpaRepository<PassengerEntity, Long> {
    Optional<PassengerEntity> findByFirstName(String firstName);
}
