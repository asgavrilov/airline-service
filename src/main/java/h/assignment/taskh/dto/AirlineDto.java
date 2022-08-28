package h.assignment.taskh.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineDto implements Serializable {
    private Integer id;
    @NotBlank(message = "airline name cannot be empty")
    private String airlineName;
    private String address;
    @Email
    private String email;
    private String managerName;
}
