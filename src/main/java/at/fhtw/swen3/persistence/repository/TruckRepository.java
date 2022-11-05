package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.services.dto.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Long> {
}
