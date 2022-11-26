package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

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

        //warehouseNextHops needs to have a warehouse or hop too
        GeoCoordinateEntity geoCoordinate2 = GeoCoordinateEntity.builder().lat(20.5).lon(16.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity2 = geoCoordinateRepository.save(geoCoordinate2);
        WarehouseEntity warehouseEntity = WarehouseEntity.builder().level(2).nextHops(new ArrayList<>()).build()
                .code("ABCD13").description("Description of Hop2").processingDelayMins(0).hopType("N").locationName("Vienna").locationCoordinates(newGeoCoordinateEntity2);
        warehouseNextHops.setWarehouse(warehouseEntity);

    }

    @Test
    void save_checkIdNotNull() {
        WarehouseNextHopsEntity newWarehouseNextHopsEntity = repo.save(warehouseNextHops);
        assertNotNull(newWarehouseNextHopsEntity.getId());

    }

}