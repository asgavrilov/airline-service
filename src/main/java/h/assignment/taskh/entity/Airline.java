package h.assignment.taskh.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "airlines")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Integer id;
    @NonNull
    @Column(name = "airline_name")
    private String airlineName;

}
