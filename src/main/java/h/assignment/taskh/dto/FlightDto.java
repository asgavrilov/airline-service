package h.assignment.taskh.dto;

import h.assignment.taskh.entity.Airline;
import h.assignment.taskh.entity.Destination;
import h.assignment.taskh.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDto {
    @NotEmpty
    private String flightNumber;
    @NotNull
    private Destination destinationFrom;
    @NotNull
    private Destination destinationTo;
    private List<Flight> connectionFlights;
    @NotNull
    private Airline airline;
}
