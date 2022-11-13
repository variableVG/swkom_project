package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<RecipientEntity, Long> {
    RecipientEntity findById(long id);
}
