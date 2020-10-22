package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.domain.Aircraft;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AircraftFactory {
    private final TypeFactory typeFactory;
    private final SeatFactory seatFactory;

    public Aircraft from(AircraftEntity entity) {
        return new Aircraft(
                entity.getCode(),
                this.typeFactory.from(entity.getType()),
                entity.getSeats().stream().map(seatFactory::from).collect(Collectors.toList()),
                null,
                null
                );
    }

    public List<Aircraft> from(List<AircraftEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
