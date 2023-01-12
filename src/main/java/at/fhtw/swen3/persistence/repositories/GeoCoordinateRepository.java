package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeoCoordinateRepository extends JpaRepository<GeoCoordinateEntity, Long> {
    @Query(value = "SELECT * "
            + "FROM geo_coordinate "
            + "ORDER BY coordinate "
            + "LIMIT 1"
            , nativeQuery = true)
    List<HopEntity> findNearestLocation(@Param("Lat") Double Lon, @Param("Lon")  Double Lat);

}
