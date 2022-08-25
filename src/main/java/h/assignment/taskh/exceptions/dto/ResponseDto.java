package h.assignment.taskh.exceptions.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto {
    private List<Violation> violations = new ArrayList<>();
    private String fieldName;
    @NonNull
    private String message;

}
