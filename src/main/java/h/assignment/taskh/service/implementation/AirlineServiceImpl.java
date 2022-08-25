package h.assignment.taskh.service.implementation;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.entity.Airline;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.exceptions.ResourceNotFoundException;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.repo.AirlineRepository;
import h.assignment.taskh.repo.FlightRepository;
import h.assignment.taskh.service.AirlineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    private final FlightRepository flightRepository;

    @Override
    public AirlineDto create(AirlineDto entity) {
        if (airlineRepository.existsAirlineByAirlineName(entity.getAirlineName())) {
            log.error("Can not create airline with airline name {}. " +
                    "Airline with this name already exists", entity.getAirlineName());
            throw new WrongInputDataException(String.format(
                    "Can not create airline with id %s." +
                            "airline with this id already exists", entity.getAirlineName()
            ));
        }
        Airline airline = Airline.builder().airlineName(entity.getAirlineName().toLowerCase())
                .build();
        var res = airlineRepository.saveAndFlush(airline);
        log.info("airline with id {} and name {} has been created", res.getId(), res.getAirlineName());
        return entityToDto(res);
    }


    @Override
    public AirlineDto read(Integer id) {
        Airline res = airlineRepository.findById(id).orElse(null);
        if (res == null) {
            throw new ResourceNotFoundException("no airline is found");
        }
        log.info("returning airline with id {} and name {} ", res.getId(), res.getAirlineName());
        return entityToDto(res);
    }

    @Override
    public List<AirlineDto> getAll() {
        List<Airline> airlines = airlineRepository.findAll();
        log.info("returning all airlines");
        return airlines
                .stream()
                .map(this::entityToDto).toList();
    }

    @Override
    public AirlineDto update(Integer id, AirlineDto newEntity) {
        AirlineDto old = read(id);
        if (old == null) {
            throw new ResourceNotFoundException("no airline is found");
        }
        Airline newAirline = Airline
                .builder()
                .id(old.getId())
                .airlineName(newEntity.getAirlineName().toLowerCase(Locale.ROOT))
                .build();
        airlineRepository.saveAndFlush(newAirline);
        log.info("airline with id {} has been updated", newAirline.getId());
        return old;
    }

    @Override
    public AirlineDto remove(Integer id) {
        AirlineDto oldDto = read(id);
        airlineRepository.deleteById(id);
        log.info("airline with id {} has been removed", oldDto.getId());
        return oldDto;
    }

    @Override
    public List<FlightDto> getAllFlightsByAirline(String airlineName) {
        Airline airline = airlineRepository.findAirlineByAirlineName(airlineName.toLowerCase());
        if (airline == null) {
            throw new ResourceNotFoundException(String.format("airline with '%s' name is not found", airlineName));
        }
        List<Flight> flights = flightRepository.findFlightsByAirlineAirlineName(airlineName);
        log.info("returning flights by airlineName {}", airlineName);
        return flights.stream().map(f -> new FlightDto(
                        f.getFlightNumber(),
                        f.getDestinationFrom(),
                        f.getDestinationTo(),
                        f.getConnectionFlights(),
                        f.getAirline()
                ))
                .toList();
    }


    private AirlineDto entityToDto(Airline airline) {
        return AirlineDto.builder()
                .id(airline.getId())
                .airlineName(airline.getAirlineName())
                .build();
    }
}
