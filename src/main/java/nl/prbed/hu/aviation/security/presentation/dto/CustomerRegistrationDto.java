package nl.prbed.hu.aviation.security.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CustomerRegistrationDto {
    @NotBlank
    @ApiModelProperty(notes = "The unique username of the customer")
    public String username;

    @Size(min = 5)
    @ApiModelProperty(notes = "The password. This must be at least 5 characters long")
    public String password;

    @NotBlank
    @ApiModelProperty(notes = "The first name or names.")
    public String firstName;

    @NotBlank
    @ApiModelProperty(notes = "The last name. With prefix")
    public String lastName;

    @NotBlank
    @ApiModelProperty(notes = "Full nationality. If the customer has multiple they must select one")
    public String nationality;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "Birth date in yyyy-MM-dd format")
    public LocalDate birthDate;

    @NotBlank
    @ApiModelProperty(notes = "Email. Must contain an @ and .")
    public String email;

    @ApiModelProperty(notes = "Phone number. Following the: +1234567890 format")
    public int phoneNumber;
}
