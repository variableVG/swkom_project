package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferwarehouseRepository extends JpaRepository<TransferwarehouseEntity, Long> {

}
