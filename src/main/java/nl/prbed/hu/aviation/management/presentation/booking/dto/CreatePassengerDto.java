package nl.prbed.hu.aviation.management.presentation.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(description = "Passenger information")
public class CreatePassengerDto {
    @ApiModelProperty(notes = "Id of the customer")
    public Long id;

    @ApiModelProperty(notes = "First name of the passenger")
    public String firstName;

    @ApiModelProperty(notes = "The last name of the passenger")
    public String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "The birth date of the passenger")
    public LocalDate birthDate;

    @ApiModelProperty(notes = "The nationality of the passenger")
    public String nationality;
}
