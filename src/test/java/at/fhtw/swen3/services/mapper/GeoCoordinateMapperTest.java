package at.fhtw.swen3.services.mapper;


import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@SpringBootTest
public class GeoCoordinateMapperTest {
    GeoCoordinateEntity geoCoordinateEntity;
    GeoCoordinate geoCoordinateDto;
    @BeforeEach
    void setUp(){
        geoCoordinateDto = GeoCoordinate.builder().lat(0.1).lon(0.2).build();
        geoCoordinateDto.setLat(80.2334);
        geoCoordinateDto.setLon(-109.234);
        geoCoordinateEntity = new GeoCoordinateEntity();

        /*
        in decimal degrees format and range from -90 to 90 for latitude
        and -180 to 180 for longitude.
        For example, Washington DC has a latitude 38.8951 and longitude -77.0364 .
         */
    }
    @Test
    void dtoToEntityTest(){
        log.info("TEST dtoToEntityTest in GeoCoordinateMapperTest");
        GeoCoordinateEntity geoCoordinateEntityTest = GeoCoordinateMapper.INSTANCE.dtoToEntity(geoCoordinateDto);
        assertEquals(geoCoordinateDto.getLat(), geoCoordinateEntityTest.getLat());
        assertEquals(geoCoordinateDto.getLon(), geoCoordinateEntityTest.getLon());

    }

    @Test
    void entityToDtoTest(){
        log.info("TEST entityToDtoTest in GeoCoordinateMapperTest");

        GeoCoordinate geoCoordinateTest = GeoCoordinateMapper.INSTANCE.entityToDto(geoCoordinateEntity);
        assertEquals(geoCoordinateEntity.getLat(), geoCoordinateTest.getLat());
        assertEquals(geoCoordinateEntity.getLon(), geoCoordinateTest.getLon());

    }
}
