package nl.prbed.hu.aviation.data.aircraft;

import nl.prbed.hu.aviation.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.domain.Aircraft;
import nl.prbed.hu.aviation.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface SpringAircraftRepository extends JpaRepository<AircraftEntity, String> {
    Optional<List<AircraftEntity>> findAircraftEntitiesByType(TypeEntity type);
}
