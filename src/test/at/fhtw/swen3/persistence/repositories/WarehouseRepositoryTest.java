package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository repo;

    @Autowired
    private HopRepository hopRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    WarehouseEntity warehouseEntity;

    @BeforeEach
    void setUp() {

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);
        HopEntity hop = new HopEntity();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(newGeoCoordinateEntity); hop.setHopType("Rawan");

        HopEntity newHop = hopRepository.save(hop);

        WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsEntity.builder().hop(newHop).traveltimeMins(15).build();
        List<WarehouseNextHopsEntity> hops = new ArrayList<>();
        hops.add(warehouseNextHopsEntity);

        warehouseEntity = WarehouseEntity.builder().level(4).nextHops(hops).build();
    }

    @Test
    void saveTest_checkId() {
        WarehouseEntity warehouseEntityTest = repo.save(warehouseEntity);
        assertNotNull(warehouseEntityTest.getId());
    }
}