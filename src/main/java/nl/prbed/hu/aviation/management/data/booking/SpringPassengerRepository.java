package nl.prbed.hu.aviation.management.data.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringPassengerRepository extends JpaRepository<PassengerEntity, Long> {
    Optional<PassengerEntity> findByEmail(String email);
}
