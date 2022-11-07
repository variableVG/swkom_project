package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository repo;

    WarehouseEntity warehouseEntity;

    @BeforeEach
    void setUp() {

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        HopEntity hop = new HopEntity();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(geoCoordinate); hop.setHopType("Rawan");
        WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsEntity.builder().hop(hop).traveltimeMins(15).build();
        List<WarehouseNextHopsEntity> hops = new ArrayList<>();
        hops.add(warehouseNextHopsEntity);

        warehouseEntity = WarehouseEntity.builder().level(4).nextHops(hops).build();
    }

    @Test
    void saveTest() {
        //WarehouseEntity warehouseEntityTest = repo.save(warehouseEntity);
        //assertNotNull(warehouseEntityTest.getId());
        //assertEquals(warehouseEntity.getLevel(), warehouseEntityTest.getLevel());
    }


}