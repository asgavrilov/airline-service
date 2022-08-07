package h.assignment.taskh.service.implementation;

import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<FlightDto> getFlightsByDest(String from, String to) {
        List<Flight> res = flightRepository.findFlightByDestinationFromAndDestinationTo(from.toLowerCase(Locale.ROOT),
                to.toLowerCase(Locale.ROOT));
        return res.stream().map(
                f -> new FlightDto(
                        f.getFlightNumber(),
                        f.getDestinationFrom(),
                        f.getDestinationTo(),
                        f.getConnectionFlight())).toList();
    }

    @Override
    public FlightDto create(FlightDto entity) {
        return new FlightDto();
    }

    @Override
    public FlightDto read(String id) {
        return new FlightDto();
    }

    @Override
    public List<FlightDto> getAll() {
        return new ArrayList<>();
    }

    @Override
    public FlightDto update(String id, FlightDto newEntity) {
        return new FlightDto();
    }

    @Override
    public FlightDto remove(String id) {
        return new FlightDto();
    }
}
