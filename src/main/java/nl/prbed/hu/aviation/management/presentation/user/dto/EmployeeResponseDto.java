package nl.prbed.hu.aviation.management.presentation.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel(description = "Details of the employee")
public class EmployeeResponseDto {
    @ApiModelProperty(notes = "The username of the employee")
    private final String username;

    @ApiModelProperty(notes = "The first name of the Employee")
    private final String firstName;

    @ApiModelProperty(notes = "The last name of the employee")
    private final String lastName;
}
