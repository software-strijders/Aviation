package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.EmployeeService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    @ApiOperation(value = "Delete an employee")
    @DeleteMapping("/{username}")
    public void deleteCustomer(@PathVariable String username) {
        this.service.deleteEmployee(username);
    }
}
