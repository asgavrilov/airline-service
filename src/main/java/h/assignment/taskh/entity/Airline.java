package h.assignment.taskh.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "airlines")
public class Airline {
    @Id
    @Column(name = "id", unique = true)
    private String airlineName;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<Flight> flights;

}
