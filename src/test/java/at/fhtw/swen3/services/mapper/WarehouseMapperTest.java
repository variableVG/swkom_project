package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class WarehouseMapperTest {
    @Test
    void dtoToEntityTest(){
        log.info("TEST dtoToEntityTest in WarehouseMapperTest");
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLat(0.1); geoCoordinate.setLon(0.2);
        Hop hop = new Hop();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(geoCoordinate); hop.setHopType("Rawan");
        List<WarehouseNextHops> nextHops = new ArrayList<>();
        WarehouseNextHops warehouseNextHops = WarehouseNextHops.builder().hop(hop).traveltimeMins(14).build();
        nextHops.add(warehouseNextHops);

        Warehouse warehouseDto = Warehouse.builder()
                .level(8).nextHops(nextHops).build();

        WarehouseEntity warehouseEntityTest = WarehouseMapper.INSTANCE.dtoToEntity(warehouseDto);

        assertEquals(warehouseEntityTest.getLevel(), warehouseDto.getLevel());
        assertEquals(warehouseEntityTest.getNextHops().size(), warehouseDto.getNextHops().size());

        //TODO test the attribute NextHops in more detail
    }


    @Test
    void entityToDtoTest(){
        log.info("TEST entityToDtoTest in WarehouseMapperTest");
        GeoCoordinateEntity geoCoordinate = new GeoCoordinateEntity();
        geoCoordinate.setLat(0.1); geoCoordinate.setLon(0.2);
        HopEntity hop = new HopEntity();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(geoCoordinate); hop.setHopType("Rawan");
        List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();
        WarehouseNextHopsEntity warehouseNextHops = new WarehouseNextHopsEntity();
        warehouseNextHops.setHop(hop); warehouseNextHops.setTraveltimeMins(14);
        nextHops.add(warehouseNextHops);

        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setLevel(8); warehouseEntity.setNextHops(nextHops);

        Warehouse warehouseDtoTest = WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);

        assertEquals(warehouseDtoTest.getLevel(), warehouseEntity.getLevel());
        assertEquals(warehouseDtoTest.getNextHops().size(), warehouseEntity.getNextHops().size());
        //TODO test the attribute NextHops in more detail
    }
}
