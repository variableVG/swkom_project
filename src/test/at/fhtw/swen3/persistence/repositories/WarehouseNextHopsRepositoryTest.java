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
class WarehouseNextHopsRepositoryTest {

    @Autowired
    WarehouseNextHopsRepository repo;

    @Autowired
    private HopRepository hopRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    WarehouseNextHopsEntity warehouseNextHops;

    @BeforeEach
    void SetUp() {
        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);

        HopEntity hop = new HopEntity();
        hop.setCode("ABCD12"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(newGeoCoordinateEntity); hop.setHopType("V");

        HopEntity newHop = hopRepository.save(hop);

        warehouseNextHops = WarehouseNextHopsEntity.builder().hop(newHop).traveltimeMins(34).build();

    }

    @Test
    void save_checkIdNotNull() {
        WarehouseNextHopsEntity newWarehouseNextHopsEntity = repo.save(warehouseNextHops);
        assertNotNull(newWarehouseNextHopsEntity.getId());

    }

}