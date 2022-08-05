package h.assignment.taskh.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "flight_number")
    //TODO номер рейса
    private String flightNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Destination destinationFrom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_to_id")
    private Destination destinationTo;


//    @ManyToOne()
////    @JoinColumn(name = "airline_id")
//    private Airline airline;

}
