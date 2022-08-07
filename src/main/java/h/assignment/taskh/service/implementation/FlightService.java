package h.assignment.taskh.service.implementation;


import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.service.CrudService;
import java.util.List;

public interface FlightService extends CrudService<FlightDto, String> {
    List<FlightDto> getFlightsByDest(String from, String to);
}
