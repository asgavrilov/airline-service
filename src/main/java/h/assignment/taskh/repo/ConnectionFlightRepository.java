package h.assignment.taskh.repo;

import h.assignment.taskh.entity.ConnectionFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionFlightRepository extends JpaRepository<ConnectionFlight, String> {
}
