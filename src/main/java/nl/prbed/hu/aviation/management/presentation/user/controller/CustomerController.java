package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.CustomerService;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerOverviewResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerResponseDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @ApiOperation(value = "Find all customers")
    @GetMapping
    public CustomerOverviewResponseDto findAll() {
        return new CustomerOverviewResponseDto(this.service.findAllCustomers());
    }

    @ApiOperation(
            value = "Find a customer",
            notes = "Provide the id of a specific customer"
    )
    @GetMapping("/{id}")
    public CustomerResponseDto findbyFirstName(@PathVariable("id") Long id) {
        var customer = this.service.findById(id);
        return new CustomerResponseDto(
                customer.getUserId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getNationality(),
                customer.getBirthdate(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    @ApiOperation(value = "Delete a customer")
    @DeleteMapping("/{username}")
    public void deleteCustomer(@PathVariable String username) {
        this.service.deleteCustomer(username);
    }
}
