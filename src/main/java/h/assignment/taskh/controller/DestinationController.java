package h.assignment.taskh.controller;

import h.assignment.taskh.dto.DestinationDto;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.service.DestinationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<DestinationDto> createDestination(@RequestBody @Valid DestinationDto destinationDto) {
        log.info("Attempt to create Destination with name {}", destinationDto.getCityName());
        DestinationDto res = destinationService.create(destinationDto);
        return res != null ? new ResponseEntity<>(res, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    ResponseEntity<DestinationDto> getDestination(@PathVariable("id") String id) {
        log.info("Attempt to get destination with {} id", id);
        return ResponseEntity.ok(destinationService.read(id));
    }

    @GetMapping
    ResponseEntity<List<DestinationDto>> getAllDestinations() {
        log.info("Attempt to get all destinations");
        List<DestinationDto> res = destinationService.getAll();
        return res != null ? ResponseEntity.ok(res) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DestinationDto> deleteDestination(@PathVariable("id") String id) {
        log.info("Attempt to remove Destination with id {}", id);
        DestinationDto removedDto = destinationService.remove(id);
        return removedDto != null ? ResponseEntity.ok(removedDto) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    ResponseEntity<DestinationDto> updateDestination(@PathVariable("id") String id,
                                                     @RequestBody @Valid DestinationDto newDestination) {
        if (!newDestination.getAirportId().equals(id.toLowerCase(Locale.ROOT))) {
            throw new WrongInputDataException(String.format(
                    "Mismatching id: value id parameter is %s and new destination id is %s",
                    id, newDestination.getAirportId()));
        }
        if (newDestination != null) {
            log.info("Attempt to update Destination with id {}", id);
            DestinationDto old = destinationService.update(id, newDestination);
            return ResponseEntity.ok(old);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
