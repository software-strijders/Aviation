package nl.prbed.hu.aviation.management.presentation.aircraft.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.AircraftService;
import nl.prbed.hu.aviation.management.application.TypeService;
import nl.prbed.hu.aviation.management.domain.Type;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.*;
import nl.prbed.hu.aviation.management.presentation.aircraft.mapper.CreateAircraftDtoMapper;
import nl.prbed.hu.aviation.management.presentation.aircraft.mapper.CreateTypeDtoMapper;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasBuilder;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasDirector;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/aircraft")
public class AircraftController {
    private final CreateAircraftDtoMapper aircraftMapper = CreateAircraftDtoMapper.instance;
    private final CreateTypeDtoMapper typeMapper = CreateTypeDtoMapper.instance;
    private final AircraftService aircraftService;
    private final TypeService typeService;
    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());

    @ApiOperation(
            value = "Delete an aircraft",
            notes = "Provide the code of the aircraft."
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@Validated @PathVariable String code) {
        this.aircraftService.deleteByCode(code);
    }

    @ApiOperation(
            value = "Delete an aircraft type",
            notes = "Provide the model name of the type. This will also delete all aircraft of this type."
    )
    @DeleteMapping("/type/{modelName}")
    public void deleteTypeByModel(@Validated @PathVariable String modelName) {
        this.aircraftService.deleteByType(modelName);
        this.typeService.deleteByName(modelName);
    }


    @ApiOperation(value = "Find all aircraft")
    @GetMapping
    public CollectionModel<EntityModel<AircraftResponseDto>> findAll() {
        var aircraft = this.aircraftService.findAll();
        return this.createCollectionModel(aircraft);
    }

    @ApiOperation(
            value = "Find all aircraft by model",
            notes = "Provide the model name of the aircraft."
    )
    @GetMapping("/{modelName}")
    public CollectionModel<EntityModel<AircraftResponseDto>> findAllByModel(@PathVariable String modelName) {
        var aircraft = this.aircraftService.findAllByType(modelName);
        return this.createCollectionModel(aircraft);
    }

    @ApiOperation(
            value = "Update aircraft information",
            notes = "Provide the code of the aircraft."
    )
    @PatchMapping({"/{code}"})
    public EntityModel<AircraftResponseDto> update(@Validated @PathVariable String code, @Validated @RequestBody UpdateAircraftDto dto) {
        var aircraft = this.aircraftService.update(code, dto.code, dto.modelName);
        var response = this.createAircraftResponseDto(aircraft);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.UPDATE, response.getCode()));
    }

    @ApiOperation(
            value = "Create an aircraft",
            notes = "Provide the details of an aircraft to create an aircraft of a type. " +
                    "Note that the type must exist before you can make an aircraft of it."
    )
    @PostMapping
    public EntityModel<AircraftResponseDto> create(@Validated @RequestBody CreateAircraftDto dto) {
        var aircraft = this.aircraftService.create(this.aircraftMapper.toAircraftStruct(dto));
        var response = this.createAircraftResponseDto(aircraft);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.CREATE, response.getCode()));
    }

    @ApiOperation(
            value = "Create an aircraft type",
            notes = "Provide the details of the type."
    )
    @PostMapping("/type")
    public EntityModel<TypeResponseDto> create(@Validated @RequestBody CreateTypeDto dto) {
        var type = this.typeService.create(this.typeMapper.toTypeStruct(dto));
        var response = this.createTypeResponseDto(type);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.CREATE, "type", response.getType().getModelName()));
    }

    private CollectionModel<EntityModel<AircraftResponseDto>> createCollectionModel(List<Aircraft> aircraft) {
        var response = aircraft.stream()
                .map(this::createAircraftResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getCode())))
                .collect(Collectors.toList());
        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL));
    }

    private AircraftResponseDto createAircraftResponseDto(Aircraft aircraft) {
        return new AircraftResponseDto(aircraft.getCode(), aircraft.getType().getModelName());
    }

    private TypeResponseDto createTypeResponseDto(Type type) {
        return new TypeResponseDto(type);
    }
}
