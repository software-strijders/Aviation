package nl.prbed.hu.aviation.presentation.flightplan.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.FlightplanService;
import nl.prbed.hu.aviation.presentation.flightplan.dto.CreateFlightplanDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanService flightplanService;

    @PostMapping("/create")
    public void create(@RequestBody CreateFlightplanDto dto) {
        flightplanService.create(
                dto.duration,
                dto.code
        );
    }
}
