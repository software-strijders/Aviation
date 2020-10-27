package nl.prbed.hu.aviation.management.presentation.flightplan.mapper;

import nl.prbed.hu.aviation.management.application.struct.FlightplanStruct;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplanDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightplanDtoMapper {
    FlightplanDtoMapper instance = Mappers.getMapper(FlightplanDtoMapper.class);

    FlightplanStruct toFlightplanStruct(FlightplanDto dto);
}
