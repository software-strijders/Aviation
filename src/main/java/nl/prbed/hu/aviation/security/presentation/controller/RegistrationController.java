package nl.prbed.hu.aviation.security.presentation.controller;

import nl.prbed.hu.aviation.security.application.UserService;
import nl.prbed.hu.aviation.security.presentation.dto.RegistrationDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@Validated @RequestBody RegistrationDto registrationDto) {
        this.userService.register(
                registrationDto.username,
                registrationDto.password,
                registrationDto.firstName,
                registrationDto.lastName
        );
    }
}
