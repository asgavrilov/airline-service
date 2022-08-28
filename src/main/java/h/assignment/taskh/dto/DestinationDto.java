package h.assignment.taskh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DestinationDto implements Serializable {
    @NotBlank
    private String airportId;
    @NotBlank
    private String country;
    @NotBlank
    private String cityName;
}
