package h.assignment.taskh.dto;

import h.assignment.taskh.entity.ConnectionFlight;
import h.assignment.taskh.entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDto {
    private String flightNumber;
    private Destination destinationFrom;
    private Destination destinationTo;
    private ConnectionFlight connectionFlight;

}
