package h.assignment.taskh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "flights")
public class Flight {
    @Id
    @Column(name = "flight_number_id", unique = true)
    private String flightNumber;

    @JoinColumn(name = "from_id")
    @OneToOne
    private Destination destinationFrom;

    @JoinColumn(name = "to_id")
    @OneToOne
    private Destination destinationTo;

    @OneToOne
    private ConnectionFlight connectionFlight;

    @ManyToOne()
    @JoinColumn(name = "airline_id")
    @JsonBackReference
    private Airline airline;

}
