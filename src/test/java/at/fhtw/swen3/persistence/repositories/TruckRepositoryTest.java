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
        /*
        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(48.129885504996).lon(16.3111537799009).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);

        truckEntity = TruckEntity.builder()
                .regionGeoJson("{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[16.3246041,48.1377922],[16.3210113,48.1379656],[16.3115412,48.1366437],[16.3033821,48.1368891],[16.3015078,48.1310419],[16.300673,48.1310602],[16.2997281,48.1290642],[16.2980514,48.1289528],[16.2990351,48.1268696],[16.2985547,48.1253233],[16.3081392,48.1201565],[16.312207,48.1195325],[16.3139266,48.1255899],[16.3185017,48.124562],[16.3248962,48.1359762],[16.3246041,48.1377922]]]]}}")
                .regionGeoJson("ABC")
                .numberPlate("W-747200")
                .description("Truck in Siebenhirten").processingDelayMins(231)
                .locationName("Siebenhirten").code("WTTA01").hopType("truck")
                .locationCoordinates(newGeoCoordinateEntity).build();

         */

    }

    @Test
    void saveTest_checkIdIsNotNull() {
        /*
        TruckEntity truckEntityTest = repo.save(truckEntity);
        assertNotNull(truckEntityTest.getId());

         */

    }

}