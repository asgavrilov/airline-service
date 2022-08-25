package h.assignment.taskh.service;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.FlightDto;


import java.util.List;

public interface AirlineService extends CrudService<AirlineDto, Integer> {
    AirlineDto create(AirlineDto entity);
    List<FlightDto> getAllFlightsByAirline(String airlineName);
}
