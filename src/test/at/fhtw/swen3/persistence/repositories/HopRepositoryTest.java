package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.persistence.entity.WarehouseNextHopsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HopRepositoryTest {

    @Autowired
    HopRepository hopRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    HopEntity hop;

    @BeforeEach
    void setUp() {

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);

        hop = new HopEntity();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(newGeoCoordinateEntity); hop.setHopType("V");


    }

    @Test
    void saveTest_checkIdNotNull() {
        HopEntity newHop = hopRepository.save(hop);
        assertNotNull(newHop.getId());

    }

}