package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ParcelRepositoryTest {

    @Autowired
    ParcelRepository repo;
    @Autowired
    RecipientRepository recipientRepository;
    ParcelEntity parcel;

    @BeforeEach
    void setUp() {
        RecipientEntity recipient = RecipientEntity.builder().name("Violeta").street("Straße A")
                .postalCode("A-1200").city("Vienna").country("Austria").build();

        //Create sender
        RecipientEntity sender = RecipientEntity.builder().name("Ibo").street("Straße B")
                .postalCode("A-1200").city("Vienna").country("Austria").build();

        recipientRepository.save(recipient);
        recipientRepository.save(sender);

        //generate TrackingId:
        String trackingId = RandomStringUtils.randomAlphabetic(9);

        parcel = ParcelEntity.builder().sender(sender).recipient(recipient).weight(23.5F).trackingId(trackingId)
                .futureHops(new ArrayList<>()).visitedHops(new ArrayList<>()).state(ParcelEntity.StateEnum.PICKUP)
                .build();


    }
    @Test
    void saveTest_checkIdIsNotNull() {
        ParcelEntity parcelTest = repo.save(parcel);
        assertNotNull(parcelTest.getId());

    }

}