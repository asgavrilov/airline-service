package h.assignment.taskh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDto implements Serializable {
    @NotEmpty
    private String flightNumber;
    @NotNull
    private DestinationDto destinationFrom;
    @NotNull
    private DestinationDto destinationTo;
    private List<FlightDto> connectionFlights;
    @NotNull
    private AirlineDto airline;
}
