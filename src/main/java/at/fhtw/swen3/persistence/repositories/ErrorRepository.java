package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ErrorRepository extends JpaRepository<ErrorEntity, Long> {
    ErrorEntity findById(long id);

}


