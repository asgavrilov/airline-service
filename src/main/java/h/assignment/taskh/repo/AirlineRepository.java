package h.assignment.taskh.repo;

import h.assignment.taskh.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    boolean existsAirlineByAirlineName(String name);

    Airline findByAirlineName(String name);

    @Query("SELECT air FROM Airline air join fetch air.flights")
    List<Airline> getAll();

}
