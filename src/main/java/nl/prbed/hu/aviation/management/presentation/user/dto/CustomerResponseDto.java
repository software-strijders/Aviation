package nl.prbed.hu.aviation.management.presentation.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details about a customer")
public class CustomerResponseDto {
    @ApiModelProperty(notes = "The id of the customer")
    private final Long id;

    @ApiModelProperty(notes = "The first name of the customer")
    private final String firstName;

    @ApiModelProperty(notes = "The last name of the customer")
    private final String lastName;

    @ApiModelProperty(notes = "The nationality of the customer")
    private final String nationality;

    @ApiModelProperty(notes = "The birthdate of the customer")
    private final LocalDate birthdate;

    @ApiModelProperty(notes = "The email of the customer")
    private final String email;

    @ApiModelProperty(notes = "The phone number of the customer")
    private final String phoneNumber;
}
