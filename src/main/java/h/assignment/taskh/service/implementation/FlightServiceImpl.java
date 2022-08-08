package h.assignment.taskh.service.implementation;

import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                        f.getConnectionFlights())).toList();
    }

    @Override
    public FlightDto create(FlightDto entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FlightDto read(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<FlightDto> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public FlightDto update(String id, FlightDto newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FlightDto remove(String id) {
        throw new UnsupportedOperationException();
    }
}
