package h.assignment.taskh.config;

import h.assignment.taskh.entity.Airline;
import h.assignment.taskh.entity.Destination;
import h.assignment.taskh.entity.Flight;
import h.assignment.taskh.repo.AirlineRepository;
import h.assignment.taskh.repo.DestinationRepository;
import h.assignment.taskh.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitialData {
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private FlightRepository flightRepository;

    @PostConstruct
    void init() {
        Destination destination1 = new Destination("kuf", "Russia", "Samara");
        Destination destination2 = new Destination("dme", "Russia", "Moscow");
        Destination destination3 = new Destination("tlv", "Israel", "Tel Aviv");
        Destination destination4 = new Destination("fra", "Germany", "Frankfurt");
        Destination destination5 = new Destination("bos", "USA", "Boston");

        destinationRepository.saveAll(List.of(
                destination1,
                destination2,
                destination3,
                destination4,
                destination5));

        Airline aeroflot = new Airline("aeroflot", null);
        Airline lufthansa = new Airline("lufthansa", null);

        Flight su111 = new Flight(
                "su111",
                destination1,
                destination2,
                null,
                aeroflot);

        Flight su222 = new Flight(
                "su222",
                destination2,
                destination3,
                null,
                aeroflot
        );

        Flight lh420 = new Flight(
                "lh420",
                destination4,
                destination5,
                null,
                lufthansa
        );

        List<Flight> connectionFlight = List.of(lh420);

        Flight lh1466 = new Flight(
                "lh1466-con",
                destination1,
                destination5,
                connectionFlight,
                lufthansa
        );

        aeroflot.setFlights(List.of(su111, su222));
        lufthansa.setFlights(List.of(lh1466, lh420));

        airlineRepository.saveAll(List.of(aeroflot, lufthansa));
        flightRepository.saveAll(List.of(su111, su222, lh1466));

    }
}
