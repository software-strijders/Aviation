package nl.prbed.hu.aviation.management.presentation.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel("Details about the employee")
public class EmployeeUpdateDto {
    @NotBlank
    @ApiModelProperty(notes = "The first name of the employee")
    public String firstName;

    @NotBlank
    @ApiModelProperty(notes = "The last name of the employee")
    public String lastName;
}
