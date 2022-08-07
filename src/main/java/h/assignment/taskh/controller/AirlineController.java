package h.assignment.taskh.controller;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.service.implementation.AirlineService;
import h.assignment.taskh.service.implementation.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static h.assignment.taskh.constants.Constants.*;



@RestController
@Slf4j
@RequestMapping(AIRLINES_BASE)
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @Autowired
    FlightService flightService;

    @PostMapping()
    AirlineDto createAirline(@RequestBody AirlineDto airlineDto) {
        log.info("Airline with name {} has been added", airlineDto.getAirlineName());
        return airlineService.create(airlineDto);
    }

    @GetMapping("/{airlineId}")
    AirlineDto getAirlineByName(@PathVariable("airlineId") String airlineId) {
        return airlineService.read(airlineId.toLowerCase(Locale.ROOT));
    }

    @GetMapping(value = FLIGHTS)
    List<FlightDto> getAllFlightsByAirline(@RequestParam("airlineName") String airlineName) {
        log.info("Getting flights with airline name = {}", airlineName);
        return airlineService.getAllFlightsByAirline(airlineName.toLowerCase(Locale.ROOT));
    }

    @GetMapping
    List<AirlineDto> getAllAirlines() {
        return airlineService.getAll();
    }

    @GetMapping(value = "flights/find/")
    List<FlightDto> getFlightsByDestinations(@RequestParam("from") String from,
                                             @RequestParam("to") String to) {
        return flightService.getFlightsByDest(from, to);
    }

    @DeleteMapping("/{airlineId}")
    AirlineDto removeAirline(@PathVariable String airlineId) {
        log.info("airline with {} id has been removed", airlineId);
        return airlineService.remove(airlineId);
    }

    @PutMapping("/{airlineId}")
    AirlineDto updateAirline(@PathVariable("airlineId") String airlineId, @RequestBody AirlineDto newAirlineData) {
        if (!newAirlineData.getAirlineName().equalsIgnoreCase(airlineId.toLowerCase(Locale.ROOT))) {
            log.error("Mismatching id: value id parameter is {} and new destination id is {}",
                    airlineId, newAirlineData.getAirlineName().toLowerCase(Locale.ROOT));
            throw new WrongInputDataException(String.format(
                    "Mismatching id: value id parameter is %s and new destination id is %s",
                    airlineId, newAirlineData.getAirlineName().toLowerCase(Locale.ROOT)));
        }
        AirlineDto old = airlineService.update(airlineId.toLowerCase(Locale.ROOT), newAirlineData);
        log.info("Airline with id {} updated", airlineId.toLowerCase());
        return old;
    }

}
