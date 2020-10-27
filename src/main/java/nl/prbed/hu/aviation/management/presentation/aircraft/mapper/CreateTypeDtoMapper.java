package nl.prbed.hu.aviation.management.presentation.aircraft.mapper;

import nl.prbed.hu.aviation.management.application.struct.TypeStruct;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.CreateTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateTypeDtoMapper {
    CreateTypeDtoMapper instance = Mappers.getMapper(CreateTypeDtoMapper.class);

    TypeStruct toTypeStruct(CreateTypeDto dto);
}
