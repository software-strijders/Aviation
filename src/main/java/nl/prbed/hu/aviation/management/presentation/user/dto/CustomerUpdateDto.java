package nl.prbed.hu.aviation.management.presentation.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CustomerUpdateDto {
    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @NotBlank
    public String nationality;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate birthDate;

    @NotBlank
    public String email;

    public int phoneNumber;
}
