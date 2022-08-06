package h.assignment.taskh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DestinationDto {
    private String airportId;
    private String country;
    private String cityName;
}
