package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipientRepositoryTest {

    @Autowired
    RecipientRepository repo;

    RecipientEntity recipient;

    @BeforeEach
    void setUp() {
         recipient = RecipientEntity.builder().name("Violeta").street("Straße A")
                .postalCode("A-1200").city("Vienna").country("Austria").build();

    }

    @Test
    void saveTest() {

        RecipientEntity recipientTest = repo.save(recipient);
        assertNotNull(recipientTest.getId());

    }


}