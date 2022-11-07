package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipientRepositoryTest {

    @Autowired
    RecipientRepository repo;

    @Test
    void setUp() {

    }

    @Test
    void saveTest() {

        RecipientEntity recipient = RecipientEntity.builder().name("Violeta").street("Straße A")
                .postalCode("A-1200").city("Vienna").country("Austria").build();

        RecipientEntity recipientTest = repo.save(recipient);
        assertNotNull(recipientTest.getId());

    }

}