package h.assignment.taskh.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FlightRepositoryTest {

    private final FlightRepository flightRepository;

    public FlightRepositoryTest(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @AfterEach
    void tearDown() {
        flightRepository.deleteAll();

    }

    @Test
    void findFlightByDestinationFromAndDestinationTo() {
        //create obj
        //validate that finds
        assertTrue(true); //just as a stub for future
    }

    @Test
    void findFlightsByAirlineAirlineName() {
        assertTrue(true); //just as a stub for future
    }

    @Test
    void existsFlightByFlightNumber() {
        assertTrue(true); //just as a stub for future
        //create obj
        //validate that exists
    }
}