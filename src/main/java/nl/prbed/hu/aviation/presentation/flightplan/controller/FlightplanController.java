package nl.prbed.hu.aviation.presentation.flightplan.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.FlightplanService;
import nl.prbed.hu.aviation.presentation.flightplan.dto.CreateFlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.ResponseFlightplanDto;
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
    public ResponseFlightplanDto create(@RequestBody CreateFlightplanDto dto) {
        var flightplan = flightplanService.create(dto.duration, dto.code);
        return new ResponseFlightplanDto(flightplan.getCode(), flightplan.getDuration());
    }
}
