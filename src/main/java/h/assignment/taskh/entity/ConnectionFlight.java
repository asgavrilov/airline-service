package h.assignment.taskh.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Builder
@AllArgsConstructor
@ToString
@Table(name = "connection_flights")
@NoArgsConstructor
public class ConnectionFlight {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @OneToOne
    private Flight flight;
}
