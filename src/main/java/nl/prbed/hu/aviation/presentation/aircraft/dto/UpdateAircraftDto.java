package nl.prbed.hu.aviation.presentation.aircraft.dto;

import javax.validation.constraints.NotBlank;

public class UpdateAircraftDto {
    @NotBlank
    public String modelName;

    @NotBlank
    public String code;
}
