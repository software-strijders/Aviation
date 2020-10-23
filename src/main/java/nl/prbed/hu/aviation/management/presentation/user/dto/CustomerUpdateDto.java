package nl.prbed.hu.aviation.management.presentation.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@ApiModel("Details about the customer")
public class CustomerUpdateDto {
    @NotBlank
    @ApiModelProperty(notes = "The first name of the customer")
    public String firstName;

    @NotBlank
    @ApiModelProperty(notes = "The last name of the customer")
    public String lastName;

    @NotBlank
    @ApiModelProperty(notes = "The nationality of the customer")
    public String nationality;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "The birthdate of the customer")
    public LocalDate birthDate;

    @NotBlank
    @ApiModelProperty(notes = "The email of the customer")
    public String email;

    @ApiModelProperty(notes = "The phone number of the customer")
    public int phoneNumber;
}
