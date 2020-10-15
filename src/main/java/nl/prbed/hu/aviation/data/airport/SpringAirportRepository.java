package nl.prbed.hu.aviation.data.airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringAirportRepository extends JpaRepository<AirportEntity, Long> {
    Optional<AirportEntity> findByCode(String code);
}
