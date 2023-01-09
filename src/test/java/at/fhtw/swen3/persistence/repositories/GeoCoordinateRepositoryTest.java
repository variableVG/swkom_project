package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.repositories.GeoCoordinateRepository;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.easymock.EasyMock;
import org.hibernate.cfg.annotations.Nullability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeoCoordinateRepositoryTest {

    @Autowired
    private GeoCoordinateRepository repo;

    GeoCoordinateEntity geoCoordinateEntity;

    @BeforeEach
    void setUp() {
        geoCoordinateEntity = GeoCoordinateEntity.builder()
                .lon(56.3)
                .lat(45.6).build();
    }

    @Test
    void saveTest_checkIdIsNotNull() {
        /*
        GeoCoordinateEntity geoCoordinateEntityTest = repo.save(geoCoordinateEntity);
        assertNotNull(geoCoordinateEntityTest.getId());

         */
    }


    @Test
    void findById_checkIsNotNull() {
        /*
        //We first need to store the GeoCoordinateEntity, so it has an Id (otherwise it will not generate an Id).
        GeoCoordinateEntity geoCoordinateEntityTest = repo.save(geoCoordinateEntity);
        //Then call the findById() method with the Id of the stored Geocoordinate.
        Optional<GeoCoordinateEntity> actualGeoCoordinate = repo.findById(geoCoordinateEntityTest.getId());
        assertNotNull(actualGeoCoordinate);

         */
    }




}