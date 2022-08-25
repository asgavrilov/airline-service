package h.assignment.taskh.controller;

import h.assignment.taskh.dto.DestinationDto;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.service.DestinationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static h.assignment.taskh.constants.Constants.DESTINATION_BASE;

@RestController
@Slf4j
@Validated
@AllArgsConstructor
@RequestMapping(value = DESTINATION_BASE)
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping
    DestinationDto createDestination(@RequestBody @Valid DestinationDto destinationDto) {
        log.info("Attempt to create Destination with name {}", destinationDto.getCityName());
        return destinationService.create(destinationDto);
    }

    @GetMapping("/{id}")
    DestinationDto getDestination(@PathVariable("id") @Valid String id) {
        log.info("Attempt to get destination with {} id", id);
        return destinationService.read(id);
    }

    @GetMapping
    List<DestinationDto> getAllDestinations() {
        log.info("Attempt to get all destinations");
        return destinationService.getAll();
    }

    @DeleteMapping("/{id}")
    DestinationDto deleteDestination(@PathVariable("id") @Valid String id) {
        DestinationDto removedDto = destinationService.remove(id);
        log.info("Attempt to remove Destination with id {}", id);
        return removedDto;
    }

    @PutMapping("/{id}")
    DestinationDto updateDestination(@PathVariable("id") String id,
                                     @RequestBody @Valid DestinationDto newDestination) {
        if (!newDestination.getAirportId().equals(id.toLowerCase(Locale.ROOT))) {
            log.error("Mismatching id: value id parameter is {} and new destination id is {}", id, newDestination.getAirportId());
            throw new WrongInputDataException(String.format(
                    "Mismatching id: value id parameter is %s and new destination id is %s",
                    id, newDestination.getAirportId()));
        }
        log.info("Attempt to update Destination with id {}", id);
        return destinationService.update(id, newDestination);
    }
}
