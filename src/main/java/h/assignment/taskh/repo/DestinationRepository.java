package h.assignment.taskh.repo;

import h.assignment.taskh.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, String> {

    @Query("select new h.assignment.taskh.dto.DestinationDto(airportId, country, cityName) from Destination where airportId =:id")
    Destination getDestination(@Param("id") String id);

}
