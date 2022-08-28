package h.assignment.taskh.service.implementation;

import h.assignment.taskh.repo.FlightRepository;
import h.assignment.taskh.service.FlightService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;
    private FlightService underTestflightService;

    @BeforeEach
    void setUp() {
//        underTestflightService = new FlightService(flightRepository);
    }

    @Test
    void getFlightsByDest() {
    }

    @Test
    void canCreateFlight() {
        //new flight

    }

    @Test
    void read() {
    }

    @Test
    @Disabled
    void getAll() {
        //when
        underTestflightService.getAll();
        //then
//        verify(flightRepository).findAll(); from assertj
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}