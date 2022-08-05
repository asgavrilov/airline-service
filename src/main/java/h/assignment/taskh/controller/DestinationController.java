package h.assignment.taskh.controller;

import h.assignment.taskh.dto.DestinationDto;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.service.DestinationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static h.assignment.taskh.constants.Constants.DESTINATION_BASE;

@RestController
@Slf4j
@RequestMapping(value = DESTINATION_BASE)
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @PostMapping
    DestinationDto createDestination(@RequestBody DestinationDto destinationDto) {
        log.info("Destination with name {} has been added", destinationDto.getCityName());
        return destinationService.create(destinationDto);
    }

    @GetMapping("/{id}")
    DestinationDto getDestination(@PathVariable("id") int id) {
        return destinationService.read(id);
    }

    @GetMapping
    List<DestinationDto> getAllDestinations() {
        return destinationService.getAll();
    }

    @DeleteMapping("/{id}")
    DestinationDto deleteDestination(@PathVariable("id") int id) {
        DestinationDto removedDto = destinationService.remove(id);
        log.info("Destination with id {} has been removed", id);
        return removedDto;
    }

    @PutMapping("/{id}")
    DestinationDto updateDestination(@PathVariable("id") int id, @RequestBody DestinationDto newDestination) {
        if (newDestination.getId() != id) {
            log.error("Mismatching id: value id parameter is {} and new destination id is {}", id, newDestination.getId());
            throw new WrongInputDataException(String.format(
                    "Mismatching id: value id parameter is %d and new destination id is %d",
                    id, newDestination.getId()
            ));
        }
        DestinationDto old = destinationService.update(id, newDestination);
        log.info("Destination with id {} updated", id);
        return old;
    }

}
