package nl.prbed.hu.aviation.management.presentation.airport.mapper;

import nl.prbed.hu.aviation.management.application.struct.AirportStruct;
import nl.prbed.hu.aviation.management.presentation.airport.dto.AirportDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateAirportDtoMapper {
    CreateAirportDtoMapper instance = Mappers.getMapper(CreateAirportDtoMapper.class);

    AirportStruct toAirportStruct(AirportDto dto);
}
