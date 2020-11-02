package nl.prbed.hu.aviation.management.presentation.flight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(description = "Details about the flight")
public class FlightDto {
    @ApiModelProperty(notes = "The code of the flight")
    public String code;

    @ApiModelProperty(notes = "The price of the flight in economy class")
    public double priceEconomy;

    @ApiModelProperty(notes = "The price of the flight in business class")
    public double priceBusiness;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @ApiModelProperty(notes = "The date and time of the departure of the flight")
    public LocalDateTime departureDateTime;

    @ApiModelProperty(notes = "The price of the flight in first class")
    public double priceFirst;

    @ApiModelProperty(notes = "The code of the aircraft")
    public String aircraftCode;

    @ApiModelProperty(notes = "The code of the flightplan")
    public String flightPlanCode;
}
