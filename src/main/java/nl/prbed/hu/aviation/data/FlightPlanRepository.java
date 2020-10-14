package nl.prbed.hu.aviation.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightPlanRepository extends JpaRepository<FlightPlanEntity, Long> {
    Optional<FlightPlanEntity> findById(Long id);
}
