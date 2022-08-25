package h.assignment.taskh.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineDto {
    private Integer id;

    @NotBlank(message = "airline name cannot be empty")
    private String airlineName;
}
