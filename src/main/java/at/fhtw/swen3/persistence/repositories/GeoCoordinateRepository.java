package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoCoordinateRepository extends JpaRepository<GeoCoordinateEntity, Long> {


}
