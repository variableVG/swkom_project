package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.OffsetDateTime;

@SpringBootTest
class HopArrivalRepositoryTest {

    @Autowired
    HopArrivalRepository repo;

    HopArrivalEntity hopArrivalEntity;

    @BeforeEach
    void setUp() {
        /*
        ParcelEntity parcel = new ParcelEntity();
        RecipientEntity recipientEntity = RecipientEntity.builder()
                .name("Rawan")
                .street("Spenger")
                .postalCode("A-1120")
                .city("vienna")
                .country("Austria")
                .build();
        RecipientEntity senderEntity = RecipientEntity.builder()
                .name("Rawan")
                .city("Damaskus")
                .country("Syrien")
                .street("masaken")
                .postalCode("A-1120").build();

         */


        hopArrivalEntity = HopArrivalEntity.builder()
                .code("XWZQ5").description("description 1")
                .dateTime(OffsetDateTime.parse("2022-10-22T12:57:59.601Z")).build();
    }

    @Test
    void saveTest() {
        HopArrivalEntity hopArrivalEntitytest = repo.save(hopArrivalEntity);
        assertNotNull(hopArrivalEntitytest.getId());

    }


}