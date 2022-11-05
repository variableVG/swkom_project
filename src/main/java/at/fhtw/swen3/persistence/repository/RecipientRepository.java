package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<RecipientEntity, Long> {
}
