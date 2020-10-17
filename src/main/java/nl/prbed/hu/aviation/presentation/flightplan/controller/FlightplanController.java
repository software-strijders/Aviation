package nl.prbed.hu.aviation.presentation.flightplan.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.FlightplanService;
import nl.prbed.hu.aviation.domain.Flightplan;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FindAllFlightplanResponseDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FlightplanFindByCodeResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanService flightplanService;

    @PostMapping
    public FlightplanFindByCodeResponseDto create(@RequestBody FlightplanDto dto) {
        return createFlightplanResponseDto(this.flightplanService.create(
                dto.code,
                dto.duration,
                dto.arrival,
                dto.destination));
    }

    @GetMapping
    public FindAllFlightplanResponseDto findAll() {
        var flightplans = this.flightplanService.findAll();
        return new FindAllFlightplanResponseDto(flightplans);
    }

    @GetMapping("/{code}")
    public FlightplanFindByCodeResponseDto findByCode(@PathVariable String code) {
        return createFlightplanResponseDto(this.flightplanService.findByCode(code));
    }

    @PutMapping
    public FlightplanFindByCodeResponseDto update(@RequestBody FlightplanDto dto) {
        return createFlightplanResponseDto(this.flightplanService.update(
                dto.code,
                dto.duration,
                dto.arrival,
                dto.destination));
    }

    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightplanService.deleteByCode(code);
    }

    private FlightplanFindByCodeResponseDto createFlightplanResponseDto(Flightplan flightplan) {
        return new FlightplanFindByCodeResponseDto(
                flightplan.getCode(),
                flightplan.getDuration(),
                flightplan.getArrival(),
                flightplan.getDestination());
    }
}
