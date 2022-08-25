package h.assignment.taskh.service.implementation;


import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.exceptions.ResourceNotFoundException;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.repo.FlightRepository;
import h.assignment.taskh.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Override
    @Transactional
    public List<FlightDto> getFlightsByDest(String from, String to) {
        List<Flight> res = flightRepository.findFlightByDestinationFromAndDestinationTo(from.toLowerCase(Locale.ROOT),
                to.toLowerCase(Locale.ROOT));
        return res.stream().map(
                f -> new FlightDto(
                        f.getFlightNumber(),
                        f.getDestinationFrom(),
                        f.getDestinationTo(),
                        f.getConnectionFlights(),
                        f.getAirline())).toList();
    }

    @Override
    public FlightDto create(FlightDto entity) {
        if (flightRepository.existsFlightByFlightNumber(entity.getFlightNumber())) {
            log.error("Can not create airline with airline name {}. " +
                    "Airline with this name already exists", entity.getFlightNumber());
            throw new WrongInputDataException(String.format(
                    "Can not create airline with id %s." +
                            "airline with this id already exists", entity.getFlightNumber()
            ));
        }
        Flight flight = Flight.builder().
        flightNumber(entity.getFlightNumber())
                .destinationFrom(entity.getDestinationFrom())
                .destinationTo(entity.getDestinationTo())
                .connectionFlights(entity.getConnectionFlights())
                .airline(entity.getAirline())
                .build();

        var res = flightRepository.saveAndFlush(flight);
        log.info("airline with id {} has been created", flight.getFlightNumber());
        return entityToDto(res);
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
        return flights.stream().map(this::entityToDto).toList();
    }

    private FlightDto entityToDto(Flight flight) {
        return FlightDto.builder()
                .flightNumber(flight.getFlightNumber())
                .destinationFrom(flight.getDestinationFrom())
                .destinationTo(flight.getDestinationTo())
                .connectionFlights(flight.getConnectionFlights())
                .airline(flight.getAirline()).build();
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
                .destinationFrom(newEntity.getDestinationFrom())
                .destinationTo(newEntity.getDestinationTo())
                .connectionFlights(newEntity.getConnectionFlights())
                .airline(newEntity.getAirline())
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
