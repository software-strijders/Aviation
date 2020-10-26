package nl.prbed.hu.aviation.management.presentation.booking.mapper;

import nl.prbed.hu.aviation.management.application.struct.BookingStruct;
import nl.prbed.hu.aviation.management.presentation.booking.dto.CreateBookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateBookingDtoMapper {
    CreateBookingDtoMapper instance = Mappers.getMapper(CreateBookingDtoMapper.class);

    BookingStruct toBookingStruct(CreateBookingDto dto);
}
