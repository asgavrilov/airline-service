package h.assignment.taskh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Flight> connectionFlights;

    @ManyToOne()
    @JoinColumn(name = "airline_id")
    @JsonBackReference
    private Airline airline;

}
