package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {

    // WarehouseEntity save(WarehouseEntity warehouseEntity);

    @Query(value = "SELECT *, 0 AS clazz_ " +
            "FROM warehouse  " +
            "         JOIN hop ON hop.id = warehouse.id " +
            "WHERE (warehouse.level = 0)" +
            ";", nativeQuery = true)
    WarehouseEntity getRoot();
}
