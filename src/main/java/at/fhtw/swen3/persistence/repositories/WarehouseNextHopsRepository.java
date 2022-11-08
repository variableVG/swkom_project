package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.WarehouseNextHopsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseNextHopsRepository extends JpaRepository<WarehouseNextHopsEntity, Long> {
    WarehouseNextHopsEntity findById(long id);
}

