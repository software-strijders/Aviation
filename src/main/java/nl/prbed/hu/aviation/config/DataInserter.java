package nl.prbed.hu.aviation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.*;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.CreateAircraftDto;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.CreateTypeDto;
import nl.prbed.hu.aviation.management.presentation.aircraft.mapper.CreateAircraftDtoMapper;
import nl.prbed.hu.aviation.management.presentation.aircraft.mapper.CreateTypeDtoMapper;
import nl.prbed.hu.aviation.management.presentation.airport.dto.AirportDto;
import nl.prbed.hu.aviation.management.presentation.airport.dto.CreateCityDto;
import nl.prbed.hu.aviation.management.presentation.airport.mapper.AirportDtoMapper;
import nl.prbed.hu.aviation.management.presentation.booking.dto.CreateBookingDto;
import nl.prbed.hu.aviation.management.presentation.booking.mapper.CreateBookingDtoMapper;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightDto;
import nl.prbed.hu.aviation.management.presentation.flight.mapper.FlightDtoMapper;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplanDto;
import nl.prbed.hu.aviation.management.presentation.flightplan.mapper.FlightplanDtoMapper;
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
    private static final String BOOKING = DIR + "booking.json";
    private static final String CITY = DIR + "city.json";
    private static final String CUSTOMER = DIR + "customer.json";
    private static final String EMPLOYEE = DIR + "employee.json";
    private static final String FLIGHT = DIR + "flight.json";
    private static final String FLIGHTPLAN = DIR + "flightplan.json";
    private static final String TYPE = DIR + "type.json";

    private final Logger logger = LoggerFactory.getLogger(DataInserter.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final AirportDtoMapper airportDtoMapper = AirportDtoMapper.instance;
    private final CreateAircraftDtoMapper createAircraftDtoMapper = CreateAircraftDtoMapper.instance;
    private final CreateTypeDtoMapper createTypeDtoMapper = CreateTypeDtoMapper.instance;
    private final CreateBookingDtoMapper createBookingDtoMapper = CreateBookingDtoMapper.instance;
    private final FlightDtoMapper flightDtoMapper = FlightDtoMapper.instance;
    private final FlightplanDtoMapper flightplanDtoMapper = FlightplanDtoMapper.instance;

    private final AircraftService aircraftService;
    private final AirportService airportService;
    private final BookingService bookingService;
    private final CityService cityService;
    private final FlightService flightService;
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
            this.insertFlights();
            this.insertBookings();
        } catch (Exception e) {
            this.logger.warn(e.getMessage());
            message = "Data already present, skipping...";
        }
        this.logger.info(message);
        this.logger.info(String.format("Elapsed time: %ds", Duration.between(start, Instant.now()).toSeconds()));
    }

    private void insertEmployees() throws IOException {
        this.executeInsertion(
                "employees",
                this.mapper.readValue(Paths.get(EMPLOYEE).toFile(), EmployeeRegisterationDto[].class),
                o -> {
                    var dto = (EmployeeRegisterationDto) o;
                    this.userService.registerEmployee(dto.username, dto.password, dto.firstName, dto.lastName);
                }
        );
    }

    private void insertCustomers() throws IOException {
        this.executeInsertion(
                "customers",
                this.mapper.readValue(Paths.get(CUSTOMER).toFile(), CustomerRegistrationDto[].class),
                o -> {
                    var dto = (CustomerRegistrationDto) o;
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
                }
        );
    }

    private void insertCities() throws IOException {
        this.executeInsertion(
                "cities",
                this.mapper.readValue(Paths.get(CITY).toFile(), CreateCityDto[].class),
                o -> {
                    var dto = (CreateCityDto) o;
                    this.cityService.create(dto.name, dto.country);
                }
         );
    }

    private void insertAirports() throws IOException {
        this.executeInsertion(
                "airports",
                this.mapper.readValue(Paths.get(AIRPORT).toFile(), AirportDto[].class),
                dto -> this.airportService.create(this.airportDtoMapper.toAirportStruct((AirportDto) dto))
        );
    }

    private void insertTypes() throws IOException {
        this.executeInsertion(
                "types",
                this.mapper.readValue(Paths.get(TYPE).toFile(), CreateTypeDto[].class),
                dto -> this.typeService.create(this.createTypeDtoMapper.toTypeStruct((CreateTypeDto) dto))
        );
    }

    private void insertAircraft() throws IOException {
        this.executeInsertion(
                "aircraft",
                this.mapper.readValue(Paths.get(AIRCRAFT).toFile(), CreateAircraftDto[].class),
                dto -> this.aircraftService.create(createAircraftDtoMapper.toAircraftStruct(((CreateAircraftDto) dto)))
        );
    }

    private void insertFlightplans() throws IOException {
        this.executeInsertion(
                "flightplans",
                this.mapper.readValue(Paths.get(FLIGHTPLAN).toFile(), FlightplanDto[].class),
                dto -> this.flightplanService.create(flightplanDtoMapper.toFlightplanStruct((FlightplanDto) dto))
        );
    }

    private void insertFlights() throws IOException {
        this.executeInsertion(
                "flights",
                this.mapper.readValue(Paths.get(FLIGHT).toFile(), FlightDto[].class),
                dto -> this.flightService.create(flightDtoMapper.toFlightStruct((FlightDto) dto))
        );
    }

    private void insertBookings() throws IOException {
        this.executeInsertion(
                "bookings",
                this.mapper.readValue(Paths.get(BOOKING).toFile(), CreateBookingDto[].class),
                dto -> {
                    this.bookingService.create(createBookingDtoMapper.toBookingStruct((CreateBookingDto) dto));
                    this.bookingService.confirmBooking(((CreateBookingDto) dto).customerId);
                }
        );
    }

    private void executeInsertion(String entityName, Object[] dtos, Inserter inserter) {
        var loadingBar = DataInsertionLoadingBar.create(entityName, dtos.length);
        for (var dto : dtos) {
            try {
                loadingBar.load();
                inserter.insert(dto);
            } catch (Exception e) {
                loadingBar.clear();
                throw e;
            }
        }
        loadingBar.done();
    }
}
