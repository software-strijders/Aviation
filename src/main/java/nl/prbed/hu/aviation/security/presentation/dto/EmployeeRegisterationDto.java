package nl.prbed.hu.aviation.security.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeRegisterationDto {
    @NotBlank
    public String username;

    @Size(min = 5)
    public String password;

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;
}