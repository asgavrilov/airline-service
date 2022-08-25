package h.assignment.taskh.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {
    private final String fieldName;

    private final String message;

}
