package h.assignment.taskh.dto;

import h.assignment.taskh.entity.Destination;
import h.assignment.taskh.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDto {
    private String flightNumber;
    private Destination destinationFrom;
    private Destination destinationTo;
    private List<Flight> connectionFlights;
}
