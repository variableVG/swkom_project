package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WarehouseServiceImplTest {

    @Autowired
    private WarehouseServiceImpl warehouseService;

    @Test
    void importWarehouses() {
        // Create WarehouseDto
        GeoCoordinate geoCoordinateDto = GeoCoordinate.builder().lat(23.5).lon(56.9).build();
        Hop hopDto = new Hop();
        hopDto.setCode("VIGG59"); hopDto.setDescription("Description of Hop");
        hopDto.setProcessingDelayMins(3); hopDto.setLocationName("Vienna");
        hopDto.setLocationCoordinates(geoCoordinateDto); hopDto.setHopType("V");

        WarehouseNextHops warehouseNextHops= WarehouseNextHops.builder().hop(hopDto).traveltimeMins(15).build();

        List<WarehouseNextHops> hops = new ArrayList<>();
        hops.add(warehouseNextHops);

        //Since warehouseEntity extends from Hopentity, we also need to implement the attributes for HopEntity.
        GeoCoordinate geoCoordinateForWarehouse = GeoCoordinate.builder().lat(24.5).lon(6.9).build();
        Warehouse warehouse= Warehouse.builder().level(4).nextHops(hops).build()
                .code("VIGG62").description("This is a description for WarehouseEntity").hopType("G")
                .processingDelayMins(10).locationName("Graz").locationCoordinates(geoCoordinateForWarehouse);

        try {
            warehouseService.importWarehouses(warehouse);
            assertTrue(true);
        } catch (Exception e) {
            fail(); // This line should be reached if the test runs properly
        }

    }
}