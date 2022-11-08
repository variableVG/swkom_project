package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.persistence.entity.TransferwarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferwarehouseRepository extends JpaRepository<TransferwarehouseEntity, Long> {
    TransferwarehouseEntity findById(long id);

}
