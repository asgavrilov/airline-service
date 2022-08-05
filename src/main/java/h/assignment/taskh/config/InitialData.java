package h.assignment.taskh.config;

import h.assignment.taskh.repo.AirlineRepository;
import h.assignment.taskh.repo.DestinationRepository;
import h.assignment.taskh.repo.FlightRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
public class InitialData {

    private AirlineRepository airlineRepository;
    private DestinationRepository destinationRepository;
    private FlightRepository flightRepository;

    @PostConstruct
    void init() {
        //TODO
    }

    @Transactional
    void saveData() {

    }
}
