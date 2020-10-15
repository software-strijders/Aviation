package nl.prbed.hu.aviation.data.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringAircraftRepository extends JpaRepository<AircraftEntity, Long> {
    Optional<List<AircraftEntity>> findAircraftEntitiesByType(TypeEntity type);
    Optional<AircraftEntity> findAircraftEntityByCode(String code);

    void deleteAircraftEntitiesByType(TypeEntity type);
}
