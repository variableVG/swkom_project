package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HopRepository extends JpaRepository<HopEntity, Long> {

    @Query(value = "SELECT * "
            + "FROM truck "
            + "ORDER BY region_geo "
            + "LIMIT 1"
            , nativeQuery = true)
    List<HopEntity> findNearestHop(@Param("Lat") Double Lon, @Param("Lon")  Double Lat);
}
