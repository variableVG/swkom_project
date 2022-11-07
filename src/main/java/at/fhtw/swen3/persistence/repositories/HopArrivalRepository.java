package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopArrivalRepository extends JpaRepository<HopArrivalEntity, Long> {

}
