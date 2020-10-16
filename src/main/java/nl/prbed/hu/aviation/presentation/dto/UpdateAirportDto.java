package nl.prbed.hu.aviation.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateAirportDto {
    @NotBlank
    public String code;

    @NotNull
    public double latitude;

    @NotNull
    public double longitude;

    @NotBlank
    public String cityName;
}
