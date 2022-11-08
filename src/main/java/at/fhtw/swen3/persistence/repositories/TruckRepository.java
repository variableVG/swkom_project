package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.TruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<TruckEntity, Long> {
    TruckEntity findById(long id);
}
