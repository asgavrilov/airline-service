package h.assignment.taskh.service.implementation;


import h.assignment.taskh.dto.CreateFlightDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.dto.helper.DtoHelper;
import h.assignment.taskh.entity.Airline;
import h.assignment.taskh.entity.Destination;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.exceptions.ResourceNotFoundException;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.repo.AirlineRepository;
import h.assignment.taskh.repo.DestinationRepository;
import h.assignment.taskh.repo.FlightRepository;
import h.assignment.taskh.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

import static h.assignment.taskh.dto.helper.DtoHelper.dtoToEntity;
import static h.assignment.taskh.dto.helper.DtoHelper.entityToDto;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private DestinationRepository destinationRepository;
    private AirlineRepository airlineRepository;

    @Override
    @Transactional
    public List<FlightDto> getFlightsByDest(String from, String to) {
        List<Flight> res = flightRepository.findFlightByDestinationFromAndDestinationTo(
                from.toLowerCase(Locale.ROOT),
                to.toLowerCase(Locale.ROOT));
        return res.stream().map(DtoHelper::entityToDto).toList();
    }

    @Override
    public FlightDto create(CreateFlightDto entity) {
        if (flightRepository.existsFlightByFlightNumber(entity.getFlightNumber())) {
            throw new WrongInputDataException(String.format(
                    "Can not create airline with id %s." +
                            "airline with this id already exists", entity.getFlightNumber()
            ));
        }
        Destination destinationFrom = destinationRepository.findById(entity.getDestinationFrom()).orElse(null);
        Destination destinationTo = destinationRepository.findById(entity.getDestinationFrom()).orElse(null);
        List<Flight> connFlights = flightRepository.findAllById(entity.getConnectionFlights());
        Airline airline = airlineRepository.findAirlineByAirlineName(entity.getAirlineId());
        Flight flight = Flight.builder().
                flightNumber(entity.getFlightNumber())
                .destinationFrom(destinationFrom)
                .destinationTo(destinationTo)
                .connectionFlights(connFlights)
                .airline(airline)
                .build();

        var res = flightRepository.saveAndFlush(flight);
        log.info("airline with id {} has been created", flight.getFlightNumber());
        return entityToDto(res);
    }

    @Override
    public FlightDto create(FlightDto entity) {
        return null;
    }

    @Override
    public FlightDto read(String id) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight == null) {
            throw new ResourceNotFoundException("no flight is found");
        }
        log.info("returning flight with id {} ", flight.getFlightNumber());
        return entityToDto(flight);
    }

    @Override
    public List<FlightDto> getAll() {
        List<Flight> flights = flightRepository.findAll();
        log.info("returning all flights");
        return flights.stream().map(DtoHelper::entityToDto).toList();
    }

    @Override
    public FlightDto update(String id, FlightDto newEntity) {
        FlightDto old = read(id);
        if (old == null) {
            throw new ResourceNotFoundException("no flight is found");
        }
        Flight newFlight = Flight
                .builder()
                .flightNumber(id)
                .destinationFrom(dtoToEntity(newEntity.getDestinationFrom()))
                .destinationTo(dtoToEntity(newEntity.getDestinationTo()))
                .connectionFlights(newEntity.getConnectionFlights().stream().map(DtoHelper::dtoToEntity).toList())
                .airline(dtoToEntity(newEntity.getAirline()))
                .build();
        flightRepository.saveAndFlush(newFlight);
        log.info("flight with id {} has been updated", newFlight.getFlightNumber());
        return old;
    }

    @Override
    public FlightDto remove(String id) {
        throw new UnsupportedOperationException();
    }
}
