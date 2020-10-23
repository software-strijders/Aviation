package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.EmployeeService;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeOverviewResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeResponseDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @ApiOperation(
            value = "Delete an employee",
            notes = "Provide the username of the user."
    )
    @DeleteMapping("/{username}")
    public void deleteCustomer(@PathVariable String username) {
        this.employeeService.deleteEmployee(username);
    }

    @ApiOperation(
            value = "Find an employee",
            notes = "Provide the username of the user."
    )
    @GetMapping("/{username}")
    public EmployeeResponseDto findByUsername(@PathVariable String username) {
        var employee = this.employeeService.findByUsername(username);
        return new EmployeeResponseDto(employee.getUsername(), employee.getFirstName(), employee.getLastName());
    }

    @ApiOperation(value = "Find all employees")
    @GetMapping
    public EmployeeOverviewResponseDto findAll() {
        var employees = this.employeeService.findAll();
        return new EmployeeOverviewResponseDto(employees);
    }
}
