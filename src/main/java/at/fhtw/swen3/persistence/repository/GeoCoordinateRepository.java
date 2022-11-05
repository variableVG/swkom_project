package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoCoordinateRepository extends JpaRepository<GeoCoordinateEntity, Long> {

}
