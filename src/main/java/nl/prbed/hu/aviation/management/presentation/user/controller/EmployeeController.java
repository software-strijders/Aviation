package nl.prbed.hu.aviation.management.presentation.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.EmployeeService;
import nl.prbed.hu.aviation.management.domain.Employee;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasBuilder;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasDirector;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeOverviewResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeResponseDto;
import nl.prbed.hu.aviation.management.presentation.user.dto.EmployeeUpdateDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/employee")
public class EmployeeController {
    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());
    private final EmployeeService service;

    @ApiOperation(
            value = "Delete an employee",
            notes = "Provide the username of the employee."
    )
    @DeleteMapping("/{username}")
    public void deleteByUsername(@PathVariable String username) {
        this.service.deleteByUsername(username);
    }

    @ApiOperation(
            value = "Find an employee",
            notes = "Provide the username of the employee."
    )
    @GetMapping("/{username}")
    public EntityModel<EmployeeResponseDto> findByUsername(@PathVariable String username) {
        var employee = this.service.findByUsername(username);
        var response = this.createResponseDto(employee);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ONE, username));
    }

    @ApiOperation(value = "Find all employees")
    @GetMapping
    public CollectionModel<EntityModel<EmployeeResponseDto>> findAll() {
        var employees = this.service.findAll();
        var response = employees.stream()
                .map(this::createResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getUsername())))
                .collect(Collectors.toList());
        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL));
    }

    @ApiOperation(
            value = "Update an employee",
            notes = "Provide the username of the employee"
    )
    @PatchMapping("/{username}")
    public EntityModel<EmployeeResponseDto> update(
            @PathVariable String username,
            @Valid @RequestBody EmployeeUpdateDto dto
    ) {
        var employee = this.service.update(username, dto.firstName, dto.lastName);
        var response = this.createResponseDto(employee);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.UPDATE, username));
    }

    private EmployeeResponseDto createResponseDto(Employee employee) {
        return new EmployeeResponseDto(employee.getUsername(), employee.getFirstName(), employee.getLastName());
    }
}
