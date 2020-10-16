package nl.prbed.hu.aviation.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateAirportDto {
    @NotBlank
    public String code;

    @NotNull
    public float latitude;

    @NotNull
    public float longitude;

    @NotBlank
    public String cityName;
}
