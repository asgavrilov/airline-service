package h.assignment.taskh.service;


import h.assignment.taskh.dto.FlightDto;
import java.util.List;

public interface FlightService extends CrudService<FlightDto, String> {
    List<FlightDto> getFlightsByDest(String from, String to);
}
