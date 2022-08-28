package h.assignment.taskh.controller;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.service.AirlineService;
import h.assignment.taskh.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static h.assignment.taskh.constants.Constants.*;


@RestController
@Validated
@Slf4j
@AllArgsConstructor
@RequestMapping(AIRLINES_BASE)
public class AirlineController {

    private AirlineService airlineService;
    private FlightService flightService;

    @PostMapping()
    ResponseEntity<AirlineDto> createAirline(@RequestBody @Valid AirlineDto airlineDto) {
        log.info("Attempt to create Airline with name {}", airlineDto.getAirlineName());
        AirlineDto res = airlineService.create(airlineDto);
        return res != null ? new ResponseEntity<>(res, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = FLIGHTS)
    ResponseEntity<FlightDto> createFlight(@RequestBody @Valid FlightDto flightDto) {
        log.info("Attempt to create Flight with flightNumber {}", flightDto.getFlightNumber());
        FlightDto res = flightService.create(flightDto);
        return res != null ? new ResponseEntity<>(res, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{airlineId}")
    ResponseEntity<AirlineDto> getAirlineById(@PathVariable("airlineId") Integer airlineId) {
        log.info("Attempt to get airline by name: {} ", airlineId);
        return ResponseEntity.ok(airlineService.read(airlineId));
    }

    @GetMapping(value = FLIGHTS)
    ResponseEntity<List<FlightDto>> getAllFlightsByAirline(@RequestParam("airlineName") @Valid String airlineName) {
        log.info("Attempt to get flights with airline name = {}", airlineName);
        List<FlightDto> res = airlineService.getAllFlightsByAirline(airlineName.toLowerCase(Locale.ROOT));
        return ResponseEntity.ok(Objects.requireNonNullElseGet(res, ArrayList::new));
    }

    @GetMapping
    ResponseEntity<List<AirlineDto>> getAllAirlines() {
        log.info("Attempt to get all airlines");
        List<AirlineDto> res = airlineService.getAll();
        return res != null ? ResponseEntity.ok(res) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = FIND_FLIGHTS_DIRECTIONS)
    ResponseEntity<?> getFlightsByDestinations(@RequestParam("from") String from,
                                               @RequestParam("to") String to) {
        log.info("Attempt to get flight(s) from {} airport to {} destination", from, to);
        if (from.isEmpty() || to.isEmpty()) {
            return ResponseEntity.badRequest().body("missing 'from' or 'to' parameter");
        } else {
            List<FlightDto> res = flightService.getFlightsByDest(from, to);
            return res != null ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body("no data");
        }
    }

    @DeleteMapping("/{airlineId}")
    ResponseEntity<AirlineDto> removeAirline(@PathVariable Integer airlineId) {
        log.info("Attempt to remove the airline with id = {} ", airlineId);
        AirlineDto res = airlineService.remove(airlineId);
        return res != null ? ResponseEntity.ok(res) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{airlineId}")
    ResponseEntity<AirlineDto> updateAirline(@PathVariable("airlineId") Integer airlineId,
                                             @RequestBody @Valid AirlineDto newAirlineData) {
        if (!newAirlineData.getId().equals(airlineId)) {
            throw new WrongInputDataException(String.format(
                    "Mismatching id: value id parameter is %s and new airline id is %s",
                    airlineId, newAirlineData.getAirlineName().toLowerCase(Locale.ROOT)));
        }
        if (newAirlineData != null) {
            log.info("Attempt to update the Airline with id {} ", airlineId);
            AirlineDto old = airlineService.update(airlineId, newAirlineData);
            return ResponseEntity.ok(old);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = FLIGHTS + "{flightNumberId}")
    ResponseEntity<FlightDto> updateFlight(@PathVariable("flightNumberId") String flightNumberId,
                                           @RequestBody @Valid FlightDto newFlightDto) {
        if (!newFlightDto.getFlightNumber().equals(flightNumberId)) {
            throw new WrongInputDataException(String.format(
                    "Mismatching id: value id parameter is %s and new flight id is %s",
                    flightNumberId, newFlightDto.getFlightNumber().toLowerCase(Locale.ROOT)));
        }
        if (newFlightDto != null) {
            log.info("Attempt to update the Flight with id {} ", flightNumberId);
            FlightDto old = flightService.update(flightNumberId, newFlightDto);
            return ResponseEntity.ok(old);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = FLIGHTS + "{flightId}")
    ResponseEntity<FlightDto> getFlightByFlightNumberId(@PathVariable("flightId") String flightNumberId) {
        log.info("Attempt to get flights with airline name = {}", flightNumberId);
        FlightDto res = flightService.read(flightNumberId);
        return res != null ? ResponseEntity.ok(res) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = FIND_FLIGHTS)
    ResponseEntity<List<FlightDto>> getAllFlights() {
        log.info("Attempt to get all flights");
        List<FlightDto> res = flightService.getAll();
        if (res != null) {
            return ResponseEntity.ok(res);
        } else {
            List<FlightDto> emptyRes = new ArrayList<>();
            return ResponseEntity.ok(emptyRes);
        }
    }
}
