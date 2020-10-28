package nl.prbed.hu.aviation.management.presentation.flight.mapper;

import nl.prbed.hu.aviation.management.application.struct.FlightStruct;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightDtoMapper {
    FlightDtoMapper instance = Mappers.getMapper(FlightDtoMapper.class);

    FlightStruct toFlightStruct(FlightDto dto);
}
