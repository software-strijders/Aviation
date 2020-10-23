package nl.prbed.hu.aviation.management.presentation.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.Employee;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class EmployeeOverviewResponseDto {
    @ApiModelProperty(notes = "The list of employees")
    private final List<Employee> employees;
}
