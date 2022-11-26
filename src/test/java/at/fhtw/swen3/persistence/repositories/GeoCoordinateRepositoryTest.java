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
        GeoCoordinateEntity geoCoordinateEntityTest = repo.save(geoCoordinateEntity);
        assertNotNull(geoCoordinateEntityTest.getId());
    }


    @Test
    void findById_checkIsNotNull() {
        //We first need to store the GeoCoordinateEntity, so it has an Id (otherwise it will not generate an Id).
        GeoCoordinateEntity geoCoordinateEntityTest = repo.save(geoCoordinateEntity);
        //Then call the findById() method with the Id of the stored Geocoordinate.
        Optional<GeoCoordinateEntity> actualGeoCoordinate = repo.findById(geoCoordinateEntityTest.getId());
        assertNotNull(actualGeoCoordinate);
    }

    @Test
    void findById_withMocks() {
        // 1) Create Mock
        GeoCoordinateRepository geoCoordinateRepositoryMock = EasyMock.createMock(GeoCoordinateRepository.class);

        // 2) Train the mock
        /** Before we use the mock, we need to "train" the mock, that is, to tell the mock what should return
         *  in each case. In this way, it will mimic the DB.
         *
         *  We have to "tell" the mock, what the function findById() should give back. That is an
         *     Optional<GeoCoordinateEntity> (in case the Entity is not found in the DB, it returns null.
         *
         *  For the function findById we need a geoCoordinate that has been already stored and has an Id.
         *  In the scenario of a Mock, this is not possible, because theoretically, we do not have a DB. Therefore,
         *  we  set the Id for geoCoordinateEntity.
         *  EasyMock.expect() instruct the Mock with what the "fake" DB should return.
         * */

        //In this case the geoCoordinate is not saved in the DB yet (it has no Id), threfore we expect null.
        EasyMock.expect(geoCoordinateRepositoryMock.findById(geoCoordinateEntity.getId())).andReturn(null);

        //In this case the geoCoordinate has been saved in the DB, and we expect a GeoCoordinateEntity.
        geoCoordinateEntity.setId(1L);
        Optional<GeoCoordinateEntity> expectedGeoCoordinateEntity = Optional.of(geoCoordinateEntity);
        EasyMock.expect(geoCoordinateRepositoryMock.findById(geoCoordinateEntity.getId())).andReturn(expectedGeoCoordinateEntity);

        // 3) Mock is now in "replay" mode. (No more "storing" of possible answers).
        EasyMock.replay(geoCoordinateRepositoryMock);

        // 4) Test with Mock
        assertEquals(geoCoordinateRepositoryMock.findById(geoCoordinateEntity.getId()), expectedGeoCoordinateEntity);

        //Test one that is not "saved" in the DB:
        GeoCoordinateEntity geoCoordinateEntity2 = GeoCoordinateEntity.builder()
                .lon(76.8)
                .lat(5.6).build();
        assertEquals(geoCoordinateRepositoryMock.findById(geoCoordinateEntity2.getId()), null);



        // 5) Verify Mock
        // This verifies that the methods were indeed called.
        EasyMock.verify(geoCoordinateRepositoryMock);

    }


}