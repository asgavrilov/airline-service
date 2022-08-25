package h.assignment.taskh.repo;

import h.assignment.taskh.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    boolean existsAirlineByAirlineName(String name);

    Airline findAirlineByAirlineName(String name);

}
