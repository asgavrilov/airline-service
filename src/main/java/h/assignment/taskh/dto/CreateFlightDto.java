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
public class CreateFlightDto implements Serializable {
    @NotEmpty
    private String flightNumber;
    @NotNull
    private String destinationFrom;
    @NotNull
    private String destinationTo;
    private List<String> connectionFlights;
    @NotNull
    private String airlineId;
}
