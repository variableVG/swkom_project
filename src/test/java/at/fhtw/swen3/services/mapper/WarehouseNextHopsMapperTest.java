package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class WarehouseNextHopsMapperTest {
    @Test
    void dtoToEntityTest(){
        log.info("TEST dtoToEntityTest in WarehouseNextHopsMapperTest");
        GeoCoordinate geoCoordinate = GeoCoordinate.builder().lat(0.1).lon(0.2).build();
        Hop hop = new Hop();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(geoCoordinate); hop.setHopType("Rawan");

        WarehouseNextHops warehouseNextHopsDto = WarehouseNextHops.builder().hop(hop).traveltimeMins(18).build();

        WarehouseNextHopsEntity warehouseNextHopsEntityTest = WarehouseNextHopsMapper.INSTANCE.dtoToEntity(warehouseNextHopsDto);

        assertEquals(warehouseNextHopsEntityTest.getTraveltimeMins(), warehouseNextHopsDto.getTraveltimeMins());
        assertEquals(warehouseNextHopsEntityTest.getHop().getCode(), warehouseNextHopsDto.getHop().getCode());
        assertEquals(warehouseNextHopsEntityTest.getHop().getDescription(), warehouseNextHopsDto.getHop().getDescription());
        assertEquals(warehouseNextHopsEntityTest.getHop().getProcessingDelayMins(), warehouseNextHopsDto.getHop().getProcessingDelayMins());
        assertEquals(warehouseNextHopsEntityTest.getHop().getLocationName(), warehouseNextHopsDto.getHop().getLocationName());
        assertEquals(warehouseNextHopsEntityTest.getHop().getLocationCoordinates().getLat(), warehouseNextHopsDto.getHop().getLocationCoordinates().getLat());
        assertEquals(warehouseNextHopsEntityTest.getHop().getLocationCoordinates().getLon(), warehouseNextHopsDto.getHop().getLocationCoordinates().getLon());
        assertEquals(warehouseNextHopsEntityTest.getHop().getHopType(), warehouseNextHopsDto.getHop().getHopType());

        //TODO test the attribute NextHops in more detail
    }

    @Test
    void entityToDtoTest(){
        log.info("TEST entityToDtoTest in WarehouseNextHopsMapperTest");
        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();
        geoCoordinateEntity.setLat(0.1); geoCoordinateEntity.setLon(0.2);
        HopEntity hopEntity = new HopEntity();
        hopEntity.setCode("VIGG59"); hopEntity.setDescription("Description of Hop");
        hopEntity.setProcessingDelayMins(3); hopEntity.setLocationName("Vienna");
        hopEntity.setLocationCoordinates(geoCoordinateEntity); hopEntity.setHopType("Rawan");

        WarehouseNextHopsEntity warehouseNextHopsEntity = new WarehouseNextHopsEntity();
        warehouseNextHopsEntity.setHop(hopEntity);
        warehouseNextHopsEntity.setTraveltimeMins(23);


        WarehouseNextHops warehouseNextHopsTest = WarehouseNextHopsMapper.INSTANCE.entityToDto(warehouseNextHopsEntity);

        assertEquals(warehouseNextHopsTest.getTraveltimeMins(), warehouseNextHopsEntity.getTraveltimeMins());
        assertEquals(warehouseNextHopsTest.getHop().getCode(), warehouseNextHopsEntity.getHop().getCode());
        assertEquals(warehouseNextHopsTest.getHop().getDescription(), warehouseNextHopsEntity.getHop().getDescription());
        assertEquals(warehouseNextHopsTest.getHop().getProcessingDelayMins(), warehouseNextHopsEntity.getHop().getProcessingDelayMins());
        assertEquals(warehouseNextHopsTest.getHop().getLocationName(), warehouseNextHopsEntity.getHop().getLocationName());
        assertEquals(warehouseNextHopsTest.getHop().getLocationCoordinates().getLat(), warehouseNextHopsEntity.getHop().getLocationCoordinates().getLat());
        assertEquals(warehouseNextHopsTest.getHop().getLocationCoordinates().getLon(), warehouseNextHopsEntity.getHop().getLocationCoordinates().getLon());
        assertEquals(warehouseNextHopsTest.getHop().getHopType(), warehouseNextHopsEntity.getHop().getHopType());

        //TODO test the attribute NextHops in more detail
    }
}
