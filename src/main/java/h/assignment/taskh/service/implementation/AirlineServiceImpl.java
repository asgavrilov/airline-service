package h.assignment.taskh.service.implementation;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.DestinationDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.entity.Airline;
import h.assignment.taskh.entity.Destination;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.exceptions.WrongInputDataException;
import h.assignment.taskh.repo.AirlineRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;



@Service
@Slf4j
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    @Transactional
    public AirlineDto create(AirlineDto entity) {
        if (airlineRepository.existsAirlineByAirlineName(entity.getAirlineName())) {
            log.error("Can not create airline with airline name {}. " +
                    "Airline with this name already exists", entity.getAirlineName());
            throw new WrongInputDataException(String.format(
                    "Can not create airline with id %d." +
                            "airline with this id already exists", entity.getId()
            ));
        }
        Airline airline = Airline.builder().airlineName(entity.getAirlineName().toLowerCase())
                .flights(
                        entity.getFlights())
                .build();
        airlineRepository.save(airline);
        return new AirlineDto(
                airline.getId(),
                airline.getAirlineName(),
                airline.getFlights());
    }


    @Override
    public AirlineDto read(Integer id) {
        return null;
    }

    @Override
    public List<AirlineDto> getAll() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines
                .stream()
                .map(d -> new AirlineDto(
                        d.getId(),
                        d.getAirlineName(),
                        d.getFlights()
                )).toList();
    }

    @Override
    public AirlineDto update(Integer id, AirlineDto newEntity) {
        return null;
    }

    @Override
    public AirlineDto remove(Integer id) {
        return null;
    }

    @Override
    public List<FlightDto> getAllFlightsByAirline(String airlineName) {
        Airline airline = airlineRepository.findByAirlineName(airlineName.toLowerCase());
        List<Flight> flights = airline.getFlights();
        return flights.stream().map(f -> new FlightDto(
                f.getId(),
                f.getFlightNumber(),
                f.getDestinationFrom(),
                f.getDestinationTo())).toList();
    }


    private Airline convertToEntity(AirlineDto entity) {
        return modelMapper.map(entity, Airline.class);
    }
}
