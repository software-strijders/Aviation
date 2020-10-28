package nl.prbed.hu.aviation.management.presentation.booking.mapper;

import nl.prbed.hu.aviation.management.application.struct.UpdateBookingStruct;
import nl.prbed.hu.aviation.management.presentation.booking.dto.UpdateBookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UpdateBookingDtoMapper {
    UpdateBookingDtoMapper instance = Mappers.getMapper(UpdateBookingDtoMapper.class);

    UpdateBookingStruct toUpdateBookingStruct(UpdateBookingDto dto);
}
