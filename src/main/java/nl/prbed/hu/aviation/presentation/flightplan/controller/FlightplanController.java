package nl.prbed.hu.aviation.presentation.flightplan.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.FlightplanService;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FindAllFlightplanResponseDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FlightplanFindByCodeResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanService flightplanService;

    //TODO: fix destination and arrival
    @PostMapping
    public FlightplanFindByCodeResponseDto create(@RequestBody FlightplanDto dto) {
        var flightplan = this.flightplanService.create(dto.duration, dto.code);
        return new FlightplanFindByCodeResponseDto(flightplan.getCode(), flightplan.getDuration(), null, null);
    }

    @GetMapping
    public FindAllFlightplanResponseDto findAll() {
        var flightplans = this.flightplanService.findAll();
        return new FindAllFlightplanResponseDto(flightplans);
    }

    //TODO: fix destination and arrival
    @GetMapping("/{code}")
    public FlightplanFindByCodeResponseDto findByCode(@PathVariable String code) {
        var flightplan = this.flightplanService.findByCode(code);
        return new FlightplanFindByCodeResponseDto(flightplan.getCode(), flightplan.getDuration(), null, null);
    }

    //TODO: fix destination and arrival
    @PutMapping
    public FlightplanFindByCodeResponseDto update(@RequestBody FlightplanDto dto) {
        var flightplan = this.flightplanService.update(dto.code, dto.duration);
        return new FlightplanFindByCodeResponseDto(flightplan.getCode(), flightplan.getDuration(), null, null);
    }

    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightplanService.deleteByCode(code);
    }
}
