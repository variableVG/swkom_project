package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository repo;

    @Autowired
    private HopRepository hopRepository;

    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    WarehouseEntity warehouseEntity;

    @BeforeEach
    void setUp() {

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);
        HopEntity hop = new HopEntity();
        hop.setCode("RIGG59"); hop.setDescription("Description of Hop in repo");
        hop.setProcessingDelayMins(5); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(newGeoCoordinateEntity); hop.setHopType("V");

        HopEntity newHop = hopRepository.save(hop);

        //Since warehouseEntity extends from HopEntity, we also need to implement the attributes for HopEntity.
        GeoCoordinateEntity geoCoordinateForWarehouse = GeoCoordinateEntity.builder().lat(5.8).lon(5.0).build();
        GeoCoordinateEntity newGeoCoordinateForWarehouse = geoCoordinateRepository.save(geoCoordinateForWarehouse);
        warehouseEntity = WarehouseEntity.builder().level(4).nextHops(new ArrayList<>()).build()
                .code("RIGG60").description("This is a description for WarehouseEntity in Repository").hopType("G")
                .processingDelayMins(10).locationName("Salzburg").locationCoordinates(newGeoCoordinateForWarehouse);


        // We also need to assign a warehouseEntity to the warehouseNextHopsEntity
        WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsEntity.builder().hop(newHop).warehouse(warehouseEntity).traveltimeMins(15).build();
        //Set the WarehouseNextHopsEntity
        List<WarehouseNextHopsEntity> hops = new ArrayList<>();
        hops.add(warehouseNextHopsEntity);
        warehouseEntity.setNextHops(hops);

    }

    @Test
    void saveTest_checkId() {
        WarehouseEntity warehouseEntityTest = repo.save(warehouseEntity);
        assertNotNull(warehouseEntityTest.getId());
    }

    @Test
    void saveTest_checkNextHops() {

        WarehouseEntity warehouseEntityTest = repo.save(warehouseEntity);

        assertNotNull(warehouseEntityTest.getNextHops());
        assertEquals(warehouseEntityTest.getNextHops().size(), warehouseEntityTest.getNextHops().size());
        assertEquals(warehouseEntityTest.getNextHops().get(0).getTraveltimeMins(), warehouseEntity.getNextHops().get(0).getTraveltimeMins());
    }

    @Test
    void request_NextHops() {
        WarehouseEntity warehouseEntityTest = repo.save(warehouseEntity);
        Optional<WarehouseEntity> warehouseEntityTest2 = repo.findById(warehouseEntityTest.getId());

        assertNotNull(warehouseEntityTest2.get().getNextHops());

    }
}