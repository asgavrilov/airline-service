package h.assignment.taskh.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@Table(name = "airlines")
@NoArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "airline_name", unique = true)
    private String airlineName;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "airline_id")
    @ToString.Exclude
    private List<Flight> flights;

}
