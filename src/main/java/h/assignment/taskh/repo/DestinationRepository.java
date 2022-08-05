package h.assignment.taskh.repo;

import h.assignment.taskh.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    @Query("select new h.assignment.taskh.dto.DestinationDto(id, cityName) from Destination where id =:id")
    Destination getDestination(@Param("id") int id);

}
