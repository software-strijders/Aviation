package nl.prbed.hu.aviation.management.presentation.user.mapper;

import nl.prbed.hu.aviation.management.application.struct.CustomerStruct;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerUpdateDtoMapper {
    CustomerUpdateDtoMapper instance = Mappers.getMapper(CustomerUpdateDtoMapper.class);

    CustomerStruct toCustomerStruct(CustomerUpdateDto dto);
}
