package nl.prbed.hu.aviation.management.data.airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringAirportRepository extends JpaRepository<AirportEntity, Long> {
    Optional<AirportEntity> findByCode(String code);
    List<AirportEntity> findAirportEntitiesByCityName(String name);
}
