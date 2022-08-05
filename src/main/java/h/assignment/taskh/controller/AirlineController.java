package h.assignment.taskh.controller;

import h.assignment.taskh.constants.Constants;
import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.service.implementation.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static h.assignment.taskh.constants.Constants.*;



@RestController
@Slf4j
@RequestMapping(AIRLINES_BASE)
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @PostMapping()
    AirlineDto createAirline(@RequestBody AirlineDto airlineDto) {
        return airlineService.create(airlineDto);
    }

    @GetMapping(value = FLIGHTS)
    List<FlightDto> getAllFlightsByAirline(@RequestParam("airlineName") String airlineName) {
        log.info("Getting flights with airline name = {}", airlineName);
        return airlineService.getAllFlightsByAirline(airlineName);
    }

    @GetMapping
    List<AirlineDto> getAllAirlines() {
        return airlineService.getAll();
    }

}
