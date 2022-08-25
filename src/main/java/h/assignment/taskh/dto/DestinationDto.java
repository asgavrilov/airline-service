package h.assignment.taskh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DestinationDto {
    @NotBlank
    private String airportId;
    @NotBlank
    private String country;
    @NotBlank
    private String cityName;
}
