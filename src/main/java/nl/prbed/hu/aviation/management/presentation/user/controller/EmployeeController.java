package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.EmployeeService;
import nl.prbed.hu.aviation.management.domain.Employee;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeOverviewResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeUpdateDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    @ApiOperation(
            value = "Delete an employee",
            notes = "Provide the username of the user."
    )
    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        this.service.deleteEmployee(username);
    }

    @ApiOperation(
            value = "Find an employee",
            notes = "Provide the username of the user."
    )
    @GetMapping("/{username}")
    public EmployeeResponseDto findByUsername(@PathVariable String username) {
        var employee = this.service.findByUsername(username);
        return new EmployeeResponseDto(employee.getUsername(), employee.getFirstName(), employee.getLastName());
    }

    @ApiOperation(value = "Find all employees")
    @GetMapping
    public EmployeeOverviewResponseDto findAll() {
        var employees = this.service.findAll();
        return new EmployeeOverviewResponseDto(employees);
    }

    @ApiOperation(value = "Update an employee")
    @PatchMapping("/{username}")
    public EmployeeResponseDto update(@PathVariable String username, @Valid @RequestBody EmployeeUpdateDto dto) {
        return this.createResponseDto(this.service.update(username, dto.firstName, dto.lastName));
    }

    private EmployeeResponseDto createResponseDto(Employee employee) {
        return new EmployeeResponseDto(employee.getUsername(), employee.getFirstName(), employee.getLastName());
    }
}
