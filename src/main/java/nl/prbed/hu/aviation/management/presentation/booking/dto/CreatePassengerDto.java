package nl.prbed.hu.aviation.management.presentation.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CreatePassengerDto {
    public String firstName;
    public String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate birthDate;

    public String nationality;
}
