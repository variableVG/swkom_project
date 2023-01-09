package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
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
    void importWarehouses() throws BLException {
        /*
        TruckEntity truck1 = TruckEntity.builder().regionGeoJson("{\"type\":\"Feature\",\"…246041,48.1377922]]]]}}")
                .numberPlate("W-747200")
                .build().hopType("truck").code("WTTA01").description("Truck in Siebenhirten").processingDelayMins(231)
                .locationName("Siebenhirten")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(48.2083537).lon(16.3725042).build());

        TruckEntity truck2 = TruckEntity.builder().regionGeoJson("{\"type\":\"Feature\",\"…2960104,48.161831]]]]}}")
                .numberPlate("W-328249")
                .build().hopType("truck").code("WTTA010").description("Truck in Atzgersdorf").processingDelayMins(50)
                .locationName("Atzgersdorf")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(48.2083537).lon(16.3725042).build());

        WarehouseNextHopsEntity warehouseNextHopsEntity5 = WarehouseNextHopsEntity.builder().traveltimeMins(435).hop(truck1).build();
        WarehouseNextHopsEntity warehouseNextHopsEntity6 = WarehouseNextHopsEntity.builder().traveltimeMins(223).hop(truck2).build();
        ArrayList<WarehouseNextHopsEntity> hops5 = new ArrayList<>();
        hops5.add(warehouseNextHopsEntity5);
        hops5.add(warehouseNextHopsEntity6);

        WarehouseEntity warehouseEntity5 = WarehouseEntity.builder().level(2).nextHops(hops5)
                .build().hopType("warehouse").code("WENB01").description("Warehouse Level 1 - Wien")
                .processingDelayMins(76).locationName("Wien")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(48.2083537).lon(16.3725042).build());

        WarehouseNextHopsEntity warehouseNextHopsEntity4 = WarehouseNextHopsEntity.builder().traveltimeMins(315).hop(warehouseEntity5).build();
        ArrayList<WarehouseNextHopsEntity> hops4 = new ArrayList<>();
        hops4.add(warehouseNextHopsEntity4);

        WarehouseEntity warehouseEntity4 = WarehouseEntity.builder().level(2).nextHops(hops4)
                .build().hopType("warehouse").code("WSTB02").description("Warehouse Level 2 - WienStadt")
                .processingDelayMins(210).locationName("WienStadt")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(48.2083537).lon(16.3725042).build());

        WarehouseNextHopsEntity warehouseNextHopsEntity3 = WarehouseNextHopsEntity.builder().traveltimeMins(498).hop(warehouseEntity4).build();
        ArrayList<WarehouseNextHopsEntity> hops3 = new ArrayList<>();
        hops3.add(warehouseNextHopsEntity3);

        WarehouseEntity warehouseEntity3 = WarehouseEntity.builder().level(3).nextHops(hops3)
                .hopType("warehouse").code("WENA03").description("Warehouse Level 3 - Wien")
                .processingDelayMins(299).locationName("Wien")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(48.2083537).lon(16.3725042).build()).build();

        WarehouseNextHopsEntity warehouseNextHopsEntity2 = WarehouseNextHopsEntity.builder().traveltimeMins(391).hop(warehouseEntity3).build();
        ArrayList<WarehouseNextHopsEntity> hops2 = new ArrayList<>();
        hops2.add(warehouseNextHopsEntity2);

        WarehouseEntity warehouseEntity2 = WarehouseEntity.builder().level(4).nextHops(hops2)
                .build().hopType("warehouse").code("WENA04").description("Warehouse Level 4 - Wien")
                .processingDelayMins(160).locationName("Wien")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(48.2083537).lon(16.3725042).build());

        WarehouseNextHopsEntity warehouseNextHopsEntity1 = WarehouseNextHopsEntity.builder().traveltimeMins(438).hop(warehouseEntity2).build();
        ArrayList<WarehouseNextHopsEntity> hops1 = new ArrayList<>();
        hops1.add(warehouseNextHopsEntity1);

        WarehouseEntity warehouseEntity = WarehouseEntity.builder().level(0).nextHops(hops1)
                .hopType("warehouse").code("AUTA05").description("Root Warehouse - Österreich")
                .processingDelayMins(186).locationName("Root")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(46.247829).lon(13.884382).build()).build();

        warehouseService.importWarehouses(warehouseEntity);

         */

    }


    @Test
    void saveHop_Truck() throws BLException {
        /*

        TruckEntity truck1 = TruckEntity.builder().regionGeoJson("{\"type\":\"Feature\",\"…246041,48.1377922]]]]}}")
                .numberPlate("W-747200")
                .build().hopType("truck").code("WTTA01").description("Truck in Siebenhirten").processingDelayMins(231)
                .locationName("Siebenhirten")
                .locationCoordinates(GeoCoordinateEntity.builder().lat(48.2083537).lon(16.3725042).build());


        HopEntity savedHop = warehouseService.saveHop(truck1);

         */

    }
}