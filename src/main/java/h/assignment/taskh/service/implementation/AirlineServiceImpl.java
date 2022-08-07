package h.assignment.taskh.service.implementation;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.entity.Airline;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.exceptions.ResourceNotFoundException;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.repo.AirlineRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    @Transactional
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
                .flights(
                        entity.getFlights())
                .build();
        airlineRepository.save(airline);
        return new AirlineDto(
                airline.getAirlineName(),
                airline.getFlights());
    }


    @Override
    public AirlineDto read(String id) {
        Airline res = airlineRepository.findById(id.toLowerCase(Locale.ROOT)).orElse(null);
        if (res == null) {
            throw new ResourceNotFoundException("no airline is found");
        }
        return new AirlineDto(res.getAirlineName(), res.getFlights());
    }

    @Override
    public List<AirlineDto> getAll() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines
                .stream()
                .map(d -> new AirlineDto(
                        d.getAirlineName(),
                        d.getFlights()
                )).toList();
    }

    @Override
    public AirlineDto update(String id, AirlineDto newEntity) {
        AirlineDto old = read(id.toLowerCase(Locale.ROOT));
        if (old == null) {
            throw new ResourceNotFoundException("no airline is found");
        }
        Airline newAirline = Airline
                .builder()
                .airlineName(old.getAirlineName().toLowerCase(Locale.ROOT))
                .flights(newEntity.getFlights())
                .build();
        airlineRepository.save(newAirline);
        return old;
    }

    @Override
    public AirlineDto remove(String id) {
        AirlineDto oldDto = read(id);
        airlineRepository.deleteById(id);
        return oldDto;
    }

    @Override
    public List<FlightDto> getAllFlightsByAirline(String airlineName) {
        Airline airline = airlineRepository.findByAirlineName(airlineName.toLowerCase());
        List<Flight> flights = airline.getFlights();
        return flights.stream().map(f -> new FlightDto(
                        f.getFlightNumber(),
                        f.getDestinationFrom(),
                        f.getDestinationTo(),
                        f.getConnectionFlight()))
                .toList();
    }
}
