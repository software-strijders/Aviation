package nl.prbed.hu.aviation.management.data.airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringCityRepository extends JpaRepository<CityEntity, Long> {
    Optional<CityEntity> findByName(String cityName);
}
