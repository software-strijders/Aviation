package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.CustomerService;
import nl.prbed.hu.aviation.management.domain.Customer;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerOverviewResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerUpdateDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @ApiOperation(
            value = "Delete a customer",
            notes = "Provide the username of the customer."
    )
    @DeleteMapping("/{username}")
    public void deleteByUsername(@PathVariable String username) {
        this.customerService.deleteCustomer(username);
    }

    @ApiOperation(value = "Find all customers")
    @GetMapping
    public CustomerOverviewResponseDto findAll() {
        return new CustomerOverviewResponseDto(this.customerService.findAllCustomers());
    }

    @ApiOperation(
            value = "Find a customer",
            notes = "Provide the id of the customer."
    )
    @GetMapping("/{id}")
    public CustomerResponseDto findbyId(@PathVariable("id") Long id) {
        return this.createResponseDto(this.customerService.findById(id));
    }

    @ApiOperation(
            value = "Update a customer",
            notes = "Provide the username of the customer."
    )
    @PatchMapping("/{username}")
    public CustomerResponseDto update(@PathVariable String username, @Valid @RequestBody CustomerUpdateDto dto) {
        return this.createResponseDto(this.customerService.update(
                username,
                dto.firstName,
                dto.lastName,
                dto.nationality,
                dto.birthDate,
                dto.email,
                dto.phoneNumber
        ));
    }

    private CustomerResponseDto createResponseDto(Customer customer) {
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
}
