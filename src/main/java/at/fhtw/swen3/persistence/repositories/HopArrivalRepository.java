package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopArrivalRepository extends JpaRepository<HopArrivalEntity, Long> {
    HopArrivalEntity findById(long id);
}
