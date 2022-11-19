package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TruckRepositoryTest {

    @Autowired
    TruckRepository repo;

    TruckEntity truckEntity;

    @Test
    void setUp() {
        truckEntity = TruckEntity.builder()
                .regionGeoJson("region Geo Json for Entity")
                .numberPlate("RA 123AC")
                .build();

    }

    @Test
    void saveTest() {
        /*
        TruckEntity truckEntityTest = repo.save(truckEntity);
        assertNotNull(truckEntityTest.getId());

         */

    }

}