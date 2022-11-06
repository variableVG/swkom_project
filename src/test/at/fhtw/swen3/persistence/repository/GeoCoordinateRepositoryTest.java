package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeoCoordinateRepositoryTest {

    @Autowired
    private GeoCoordinateRepository repo;

    GeoCoordinateEntity geoCoordinateEntity;

    @Test
    void setUp() {
        geoCoordinateEntity = GeoCoordinateEntity.builder()
                .id(Long.valueOf(12))
                .lon(56.3)
                .lat(45.6).build();
    }

    @Test
    void saveTest_checkIdIsNotNull() {
        /*
        GeoCoordinateEntity geoCoordinateEntityTest = repo.save(geoCoordinateEntity);
        assertEquals(geoCoordinateEntity.getId(), geoCoordinateEntityTest.getId());
        assertEquals(geoCoordinateEntity.getLat(), geoCoordinateEntityTest.getLat());
        assertEquals(geoCoordinateEntity.getLon(), geoCoordinateEntityTest.getLon());

         */

    }

}