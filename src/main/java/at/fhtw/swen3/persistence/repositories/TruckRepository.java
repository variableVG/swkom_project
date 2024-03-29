package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TruckRepository extends JpaRepository<TruckEntity, Long> {

    @Query(value = "SELECT id "
            + "FROM truck "
            + "WHERE ST_Contains(region_geo, ST_MakePoint(:Lon, :Lat))"
            , nativeQuery = true)
    Integer findNearestTruck(@Param("Lat") Double Lat, @Param("Lon")  Double Lon);


}
