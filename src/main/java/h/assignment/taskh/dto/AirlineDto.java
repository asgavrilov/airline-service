package h.assignment.taskh.dto;

import h.assignment.taskh.entity.Flight;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirlineDto {
    private String airlineName;
    private List<Flight> flights;
}
