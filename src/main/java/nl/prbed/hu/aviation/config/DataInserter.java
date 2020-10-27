package nl.prbed.hu.aviation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.*;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.CreateAircraftDto;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.CreateTypeDto;
import nl.prbed.hu.aviation.management.presentation.airport.dto.AirportDto;
import nl.prbed.hu.aviation.management.presentation.airport.dto.CreateCityDto;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplanDto;
import nl.prbed.hu.aviation.security.application.UserService;
import nl.prbed.hu.aviation.security.presentation.dto.CustomerRegistrationDto;
import nl.prbed.hu.aviation.security.presentation.dto.EmployeeRegisterationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class DataInserter {
    private static final String DIR = "test-data/";
    private static final String AIRCRAFT = DIR + "aircraft.json";
    private static final String AIRPORT = DIR + "airport.json";
    private static final String CITY = DIR + "city.json";
    private static final String CUSTOMER = DIR + "customer.json";
    private static final String EMPLOYEE = DIR + "employee.json";
    private static final String FLIGHTPLAN = DIR + "flightplan.json";
    private static final String TYPE = DIR + "type.json";

    private final Logger logger = LoggerFactory.getLogger(DataInserter.class);
    private final ObjectMapper mapper = new ObjectMapper();

    private final AircraftService aircraftService;
    private final AirportService airportService;
    private final CityService cityService;
    private final FlightplanService flightplanService;
    private final UserService userService;
    private final TypeService typeService;

    @EventListener(ApplicationReadyEvent.class)
    public void insertData() {
        this.logger.info("Starting entity insertion, this might take a while...");
        var start = Instant.now();
        var message = "Data insertion complete!";
        try {
            this.insertEmployees();
            this.insertCustomers();
            this.insertCities();
            this.insertAirports();
            this.insertTypes();
            this.insertAircraft();
            this.insertFlightplans();
        } catch (Exception e) {
            this.logger.warn(e.getMessage());
            message = "Data already present, skipping...";
        }
        this.logger.info(message);
        this.logger.info(String.format("Elapsed time: %ds", Duration.between(start, Instant.now()).toSeconds()));
    }

    private void insertEmployees() {
        this.logger.info("Inserting employees...");
        try {
            var dtos = this.mapper.readValue(Paths.get(EMPLOYEE).toFile(), EmployeeRegisterationDto[].class);
            for (var dto : dtos)
                this.userService.registerEmployee(dto.username, dto.password, dto.firstName, dto.lastName);
        } catch (IOException e) {
            this.logger.warn(e.getLocalizedMessage());
        }
        this.logger.info("Inserting complete!");
    }

    private void insertCustomers() {
        this.logger.info("Inserting customers...");
        try {
            var dtos = this.mapper.readValue(Paths.get(CUSTOMER).toFile(), CustomerRegistrationDto[].class);
            for (var dto : dtos)
                this.userService.registerCustomer(
                        dto.username,
                        dto.password,
                        dto.firstName,
                        dto.lastName,
                        dto.nationality,
                        dto.birthDate,
                        dto.email,
                        dto.phoneNumber
                );
        } catch (IOException e) {
            this.logger.warn(e.getLocalizedMessage());
        }
        this.logger.info("Inserting complete!");
    }

    private void insertCities() {
        this.logger.info("Inserting cities...");
        try {
            var dtos = this.mapper.readValue(Paths.get(CITY).toFile(), CreateCityDto[].class);
            for (var dto : dtos)
                this.cityService.create(dto.name, dto.country);
        } catch (IOException e) {
            this.logger.warn(e.getLocalizedMessage());
        }
        this.logger.info("Inserting complete!");
    }

    private void insertAirports() {
        this.logger.info("Inserting airports...");
        try {
            var dtos = this.mapper.readValue(Paths.get(AIRPORT).toFile(), AirportDto[].class);
            for (var dto : dtos)
                this.airportService.create(dto.code, dto.longitude, dto.latitude, dto.cityName);
        } catch (IOException e) {
            this.logger.warn(e.getLocalizedMessage());
        }
        this.logger.info("Inserting complete!");
    }

    private void insertTypes() {
        this.logger.info("Inserting types...");
        try {
            var dtos = this.mapper.readValue(Paths.get(TYPE).toFile(), CreateTypeDto[].class);
            for (var dto : dtos)
                this.typeService.create(dto.modelName, dto.manufacturer, dto.fuelCapacity, dto.fuelConsumption);
        } catch (IOException e) {
            this.logger.warn(e.getLocalizedMessage());
        }
        this.logger.info("Inserting complete!");
    }

    private void insertAircraft() {
        this.logger.info("Inserting aircraft...");
        try {
            var dtos = this.mapper.readValue(Paths.get(AIRCRAFT).toFile(), CreateAircraftDto[].class);
            for (var dto : dtos)
                this.aircraftService.create(
                        dto.code,
                        dto.modelName,
                        dto.seatsFirst,
                        dto.seatsBusiness,
                        dto.seatsEconomy,
                        dto.airportCode
                );
        } catch (IOException e) {
            this.logger.warn(e.getLocalizedMessage());
        }
        this.logger.info("Inserting complete!");
    }

    private void insertFlightplans() {
        this.logger.info("Inserting flightplans...");
        try {
            var dtos = this.mapper.readValue(Paths.get(FLIGHTPLAN).toFile(), FlightplanDto[].class);
            for (var dto : dtos)
                this.flightplanService.create(dto.code, dto.duration, dto.departure, dto.destination);
        } catch (IOException e) {
            this.logger.warn(e.getLocalizedMessage());
        }
        this.logger.info("Inserting complete!");
    }
}
