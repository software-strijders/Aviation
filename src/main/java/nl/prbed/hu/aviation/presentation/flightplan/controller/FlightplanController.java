package nl.prbed.hu.aviation.presentation.flightplan.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.FlightplanService;
import nl.prbed.hu.aviation.presentation.flightplan.dto.CreateFlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.GetFlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.ResponseFlightplanDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanService flightplanService;

    //TODO: fix destination and arrival
    @PostMapping("/create")
    public ResponseFlightplanDto create(@RequestBody CreateFlightplanDto dto) {
        var flightplan = flightplanService.create(dto.duration, dto.code);
        return new ResponseFlightplanDto(flightplan.getCode(), flightplan.getDuration());
    }

    //TODO: fix destination and arrival
    @GetMapping("/get")
    public ResponseFlightplanDto get(@RequestBody GetFlightplanDto dto) {
        var flightplan = flightplanService.get(dto.code);
        return new ResponseFlightplanDto(flightplan.getCode(), flightplan.getDuration());
    }
}
