package nl.prbed.hu.aviation.management.presentation.aircraft.mapper;

import nl.prbed.hu.aviation.management.application.struct.AircraftStruct;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.CreateAircraftDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateAircraftDtoMapper {
    CreateAircraftDtoMapper instance = Mappers.getMapper(CreateAircraftDtoMapper.class);

    AircraftStruct toAircraftStruct(CreateAircraftDto dto);
}
