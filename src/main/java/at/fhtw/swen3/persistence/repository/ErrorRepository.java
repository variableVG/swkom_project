package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<ErrorEntity, Long> {

}
