package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HopRepository extends JpaRepository<HopEntity, Long> {

    @Query(value = "SELECT *, 0 AS clazz_ " +
            "FROM hop " +
            "         JOIN geo_coordinate ON hop.location_coordinates_id = geo_coordinate.id " +
            "ORDER BY ST_Distance(ST_MakePoint(CAST(geo_coordinate.lon AS float8), CAST(geo_coordinate.lat AS float8)), ST_MakePoint(:lon, :lat)) " +
            "LIMIT 1;", nativeQuery = true)
    HopEntity findNearestHop(@Param("lat") Double lat, @Param("lon") Double lon);

    @Query(value = "SELECT *, 0 AS clazz_ " +
            "FROM hop  " +
            "         JOIN warehouse_next_hops ON hop.id = warehouse_next_hops.warehouse_id " +
            "WHERE (warehouse_next_hops.next_hop_id = :currentId)" +
            ";", nativeQuery = true)
    HopEntity getPreviousHops(@Param("currentId") Long currentId);

    @Query(value = "SELECT *, 0 AS clazz_ " +
            "FROM hop  " +
            "         JOIN warehouse_next_hops ON hop.id = warehouse_next_hops.next_hop_id " +
            "WHERE (warehouse_next_hops.warehouse_id = :currentId)" +
            ";", nativeQuery = true)
    List<HopEntity> getNextHops(@Param("currentId") Long currentId);

    /*
    @Query(value = "SELECT * "
            + "FROM truck "
            + "ORDER BY region_geo "
            + "LIMIT 1"
            , nativeQuery = true)
    List<HopEntity> findNearestHop(@Param("Lat") Double Lon, @Param("Lon")  Double Lat);

     */


}
