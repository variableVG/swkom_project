package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);


        transferwarehouseEntity = TransferwarehouseEntity.builder()
                .regionGeoJson("region Geo Json for Entity")
                .logisticsPartnerUrl("logistics Partner url")
                .logisticsPartner("logistics Partner")
                .build()
                .description("This is a transferwarehouse").processingDelayMins(32)
                .locationName("Somewhere").code("RAWA12").hopType("R")
                .locationCoordinates(newGeoCoordinateEntity);


    }

    @Test
    void saveTest_checkIdIsNotNull() {

        TransferwarehouseEntity TransferwarehouseEntityTest = repo.save(transferwarehouseEntity);
        assertNotNull(TransferwarehouseEntityTest.getId());


    }

}