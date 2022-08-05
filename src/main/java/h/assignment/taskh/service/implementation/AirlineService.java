package h.assignment.taskh.service.implementation;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.service.CrudService;


import java.util.List;

public interface AirlineService extends CrudService<AirlineDto, Integer> {
    List<FlightDto> getAllFlightsByAirline(String airlineName);
}
