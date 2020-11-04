package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.CustomerService;
import nl.prbed.hu.aviation.management.domain.user.Customer;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasBuilder;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasDirector;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.CustomerUpdateDto;
import nl.prbed.hu.aviation.management.presentation.user.mapper.CustomerUpdateDtoMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/customer")
public class CustomerController {
    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());
    private final CustomerService service;
    private final CustomerUpdateDtoMapper mapper = CustomerUpdateDtoMapper.instance;

    @ApiOperation(
            value = "Delete a customer",
            notes = "Provide the username of the customer."
    )
    @DeleteMapping("/{username}")
    public void deleteByUsername(@PathVariable String username) {
        this.service.deleteCustomer(username);
    }

    @ApiOperation(value = "Find all customers")
    @GetMapping
    public CollectionModel<EntityModel<CustomerResponseDto>> findAll() {
        var customers = this.service.findAllCustomers();
        var response = customers.stream()
                .map(this::createResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getId().toString())))
                .collect(Collectors.toList());
        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL));
    }

    @ApiOperation(
            value = "Find a customer",
            notes = "Provide the id of the customer."
    )
    @GetMapping("/{id}")
    public EntityModel<CustomerResponseDto> findbyId(@PathVariable("id") Long id) {
        var customer = this.service.findById(id);
        var response = this.createResponseDto(customer);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ONE, response.getId().toString()));
    }

    @ApiOperation(
            value = "Update a customer",
            notes = "Provide the username of the customer."
    )
    @PatchMapping("/{username}")
    public EntityModel<CustomerResponseDto> update(@PathVariable String username, @Valid @RequestBody CustomerUpdateDto dto) {
        var customer = this.service.update(username, this.mapper.toCustomerStruct(dto));
        var response = this.createResponseDto(customer);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.UPDATE, username));
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
