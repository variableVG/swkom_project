package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {

}
