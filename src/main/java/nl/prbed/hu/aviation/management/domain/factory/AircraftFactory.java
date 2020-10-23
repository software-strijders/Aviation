package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AircraftFactory {
    private final TypeFactory typeFactory;
    private final SeatFactory seatFactory;

    public Aircraft from(AircraftEntity entity) {
        return Aircraft.create()
                .code(entity.getCode())
                .type(this.typeFactory.from(entity.getType()))
                .seats(entity.getSeats().stream().map(seatFactory::from).collect(Collectors.toList()))
                .build();
    }

    public List<Aircraft> from(List<AircraftEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
