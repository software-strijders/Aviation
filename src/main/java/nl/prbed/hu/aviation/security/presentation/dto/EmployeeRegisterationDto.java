package nl.prbed.hu.aviation.security.presentation.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeRegisterationDto {
    @NotBlank
    @ApiModelProperty(notes = "The unique username of the employee")
    public String username;

    @ApiModelProperty(notes = "The password. This must be at least 5 characters long")
    @Size(min = 5)
    public String password;

    @ApiModelProperty(notes = "The first name or names.")
    @NotBlank
    public String firstName;

    @ApiModelProperty(notes = "The last name. With prefix")
    @NotBlank
    public String lastName;
}
