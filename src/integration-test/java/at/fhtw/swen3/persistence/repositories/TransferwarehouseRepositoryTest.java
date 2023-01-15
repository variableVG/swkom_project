package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TransferwarehouseRepositoryTest {

    @Autowired
    TransferwarehouseRepository repo;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    TransferwarehouseEntity transferwarehouseEntity;

    @BeforeEach
    void setUp() {
        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(16.3246041).lon(48.1377922).build();
        geoCoordinate.setCoordinates();
        GeoCoordinateEntity newGeoCoordinate = geoCoordinateRepository.save(geoCoordinate);

        transferwarehouseEntity = TransferwarehouseEntity.builder()
                .regionGeoJson("{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[16.3246041,48.1377922],[16.3210113,48.1379656],[16.3115412,48.1366437],[16.3033821,48.1368891],[16.3015078,48.1310419],[16.300673,48.1310602],[16.2997281,48.1290642],[16.2980514,48.1289528],[16.2990351,48.1268696],[16.2985547,48.1253233],[16.3081392,48.1201565],[16.312207,48.1195325],[16.3139266,48.1255899],[16.3185017,48.124562],[16.3248962,48.1359762],[16.3246041,48.1377922]]]]}}")
                .logisticsPartnerUrl("http://our-partner-in.switzerland.com")
                .logisticsPartner("Switzerland Logistics Company")
                .description("Transferwarehouse for Switzerland").processingDelayMins(32)
                .locationName("Transferwarehouse 04 - Switzerland").code("TWXX04").hopType("transferwarehouse")
                .locationCoordinates(newGeoCoordinate).build();

    }

    @Test
    void saveTest_checkIdIsNotNull() {

        /*TransferwarehouseEntity TransferwarehouseEntityTest = repo.save(transferwarehouseEntity);
        assertNotNull(TransferwarehouseEntityTest.getId());*/
    }

}