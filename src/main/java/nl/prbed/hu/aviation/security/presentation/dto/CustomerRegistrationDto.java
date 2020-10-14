package nl.prbed.hu.aviation.security.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CustomerRegistrationDto {
    @NotBlank
    public String username;

    @Size(min = 5)
    public String password;

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
