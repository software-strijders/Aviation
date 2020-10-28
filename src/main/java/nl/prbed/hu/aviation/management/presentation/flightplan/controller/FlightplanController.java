package nl.prbed.hu.aviation.management.presentation.flightplan.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.application.FlightplanService;
import nl.prbed.hu.aviation.management.domain.flight.Flightplan;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplanDto;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplanResponseDto;
import nl.prbed.hu.aviation.management.presentation.flightplan.mapper.FlightplanDtoMapper;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasBuilder;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasDirector;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanDtoMapper mapper = FlightplanDtoMapper.instance;
    private final FlightplanService flightplanService;
    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());

    @ApiOperation(
            value = "Delete a flightplan",
            notes = "Provide the code of the flightplan."
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightplanService.deleteByCode(code);
    }

    @ApiOperation(value = "Find all flightplans")
    @GetMapping
    public CollectionModel<EntityModel<FlightplanResponseDto>> findAll() {
        var flightplans = this.flightplanService.findAll();
        var response = flightplans.stream()
                .map(this::createFlightplanResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getCode())))
                .collect(Collectors.toList());

        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL, ""));
    }

    @ApiOperation(
            value = "Find a flightplan",
            notes = "Provide the code of the flightplan."
    )
    @GetMapping("/{code}")
    public EntityModel<FlightplanResponseDto> findByCode(@PathVariable String code) {
        var flightplan = this.flightplanService.findFlightplanByCode(code);
        var response = this.createFlightplanResponseDto(flightplan);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ONE, response.getCode()));
    }

    @ApiOperation(
            value = "Update a flightplan",
            notes = "Provide the code of the flightplan."
    )
    @PatchMapping("/{code}")
    public EntityModel<FlightplanResponseDto> update(@PathVariable String code, @RequestBody FlightplanDto dto) {
        var flightplan = this.flightplanService.update(code, this.mapper.toFlightplanStruct(dto));
        var response = this.createFlightplanResponseDto(flightplan);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.UPDATE, response.getCode()));
    }

    @ApiOperation(
            value = "Create a flightplan",
            notes = "Provide the details of the flightplan. " +
                    "Note that the cities provided must exist before the flightplan can be created."
    )
    @PostMapping
    public EntityModel<FlightplanResponseDto> create(@RequestBody FlightplanDto dto) {
        var flightplan = this.flightplanService.create(this.mapper.toFlightplanStruct(dto));
        var response = this.createFlightplanResponseDto(flightplan);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.CREATE, response.getCode()));
    }

    private FlightplanResponseDto createFlightplanResponseDto(Flightplan flightplan) {
        return new FlightplanResponseDto(
                flightplan.getCode(),
                flightplan.getDuration(),
                flightplan.getDeparture(),
                flightplan.getDestination()
        );
    }
}
