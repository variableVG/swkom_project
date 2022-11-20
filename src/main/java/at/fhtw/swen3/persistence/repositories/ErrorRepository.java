package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ErrorRepository extends JpaRepository<ErrorEntity, Long> {
    ErrorEntity findById(long id);

}


