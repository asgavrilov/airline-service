package h.assignment.taskh.repo;

import h.assignment.taskh.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    @Query("select f from Flight f where f.destinationFrom.airportId = :from and f.destinationTo.airportId = :to")
    List<Flight> findFlightByDestinationFromAndDestinationTo(String from, String to);
}
