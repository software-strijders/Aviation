package nl.prbed.hu.aviation.security.presentation.controller;

import nl.prbed.hu.aviation.security.application.UserService;
import nl.prbed.hu.aviation.security.presentation.dto.CustomerRegistrationDto;
import nl.prbed.hu.aviation.security.presentation.dto.EmployeeRegisterationDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer")
    public void registerCustomer(@Validated @RequestBody CustomerRegistrationDto registrationDto) {
        this.userService.registerCustomer(
                registrationDto.username,
                registrationDto.password,
                registrationDto.firstName,
                registrationDto.lastName,
                registrationDto.nationality,
                registrationDto.birthDate,
                registrationDto.email,
                registrationDto.phoneNumber
        );
    }

    @PostMapping("/employee")
    public void register(@Validated @RequestBody EmployeeRegisterationDto registrationDto) {
        this.userService.registerEmployee(
                registrationDto.username,
                registrationDto.password,
                registrationDto.firstName,
                registrationDto.lastName
        );
    }
}
