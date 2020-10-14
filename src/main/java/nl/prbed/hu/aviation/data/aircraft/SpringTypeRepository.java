package nl.prbed.hu.aviation.data.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringTypeRepository extends JpaRepository<TypeEntity, Long> {
    Optional<TypeEntity> findByModelName(String modelName);
}
