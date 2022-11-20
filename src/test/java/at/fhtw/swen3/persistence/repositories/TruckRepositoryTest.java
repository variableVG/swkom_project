package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TruckRepositoryTest {

    @Autowired
    TruckRepository repo;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    TruckEntity truckEntity;

    @BeforeEach
    void setUp() {
        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);

        truckEntity = TruckEntity.builder()
                .regionGeoJson("region Geo Json for Entity")
                .numberPlate("RA 123AC")
                .build()
                .description("This is a truck").processingDelayMins(32)
                .locationName("Somewhere").code("VIGG59").hopType("V")
                .locationCoordinates(newGeoCoordinateEntity);

    }

    @Test
    void saveTest_checkIdIsNotNull() {
        TruckEntity truckEntityTest = repo.save(truckEntity);
        assertNotNull(truckEntityTest.getId());

    }

}