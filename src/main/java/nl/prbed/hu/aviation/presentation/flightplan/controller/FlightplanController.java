package nl.prbed.hu.aviation.presentation.flightplan.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.FlightplanService;
import nl.prbed.hu.aviation.presentation.flightplan.dto.CreateFlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.ResponseFindAllFlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.ResponseFindByCodeFlightplanDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanService flightplanService;

    //TODO: fix destination and arrival
    @PostMapping
    public ResponseFindByCodeFlightplanDto create(@RequestBody CreateFlightplanDto dto) {
        var flightplan = this.flightplanService.create(dto.duration, dto.code);
        return new ResponseFindByCodeFlightplanDto(flightplan.getCode(), flightplan.getDuration(), null, null);
    }

    @GetMapping
    public ResponseFindAllFlightplanDto findAll() {
        var flightplans = this.flightplanService.findAll();
        return new ResponseFindAllFlightplanDto(flightplans);
    }

    //TODO: fix destination and arrival
    @GetMapping("/{code}")
    public ResponseFindByCodeFlightplanDto findByCode(@PathVariable String code) {
        var flightplan = this.flightplanService.findByCode(code);
        return new ResponseFindByCodeFlightplanDto(flightplan.getCode(), flightplan.getDuration(), null, null);
    }

    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightplanService.deleteByCode(code);
    }
}
