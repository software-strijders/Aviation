package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.CustomerService;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerOverviewResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
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
            notes = "Provide an id of a specific customer"
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
}
