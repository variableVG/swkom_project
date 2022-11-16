package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ErrorRepositoryTest {

    @Autowired
    private ErrorRepository repo;

    ErrorEntity errorEntity;

    @BeforeEach
    void setUp() {
        errorEntity = ErrorEntity.builder()
                .errorMessage("This is a message").build();
    }

    @Test
    void saveTest() {
        log.info("TEST saveTest in ErrorRepositoryTest");
        ErrorEntity errorEntityTest = repo.save(errorEntity);
        assertEquals(errorEntity.getErrorMessage(), errorEntityTest.getErrorMessage());
    }

}