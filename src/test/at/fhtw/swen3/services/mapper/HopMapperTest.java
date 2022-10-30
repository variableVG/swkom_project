package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class HopMapperTest {
    HopEntity hopEntity;
    Hop hopDto;
    // GeoCoordinate geoCoordinatedto;

    @BeforeEach
    void setUp(){
        hopDto = new Hop();
        hopEntity = new HopEntity();
        // geoCoordinatedto = new GeoCoordinate();

        hopDto.setHopType("FirstHoptype");
        hopDto.setCode("RM123");
        hopDto.setDescription("fast123");
        hopDto.setProcessingDelayMins(23);
        hopDto.setLocationName("Homs");
        // geoCoordinatedto.setLon(12.3);
        // geoCoordinatedto.setLat(34.4);
        // hopDto.setLocationCoordinates(geoCoordinatedto);

    }

    @Test
    void dtoToEntityTest(){

        HopEntity hopEntityTest = HopMapper.INSTANCE.dtoToEntity(hopDto);
        assertEquals(hopDto.getHopType(), hopEntityTest.getHopType());
        assertEquals(hopDto.getCode(), hopEntityTest.getCode());
        assertEquals(hopDto.getDescription(), hopEntityTest.getDescription());
        assertEquals(hopDto.getLocationName(), hopEntityTest.getLocationName());
        //assertEquals(hopDto.getLocationCoordinates(), hopEntityTest.getLocationCoordinates());
        assertEquals(hopDto.getProcessingDelayMins(), hopEntityTest.getProcessingDelayMins());


    }
    @Test
    void entityToDtoTest(){

        Hop hopTest = HopMapper.INSTANCE.entityToDto(hopEntity);
        assertEquals(hopEntity.getHopType(), hopTest.getHopType());
        assertEquals(hopEntity.getCode(), hopTest.getCode());
        assertEquals(hopEntity.getDescription(), hopTest.getDescription());
        assertEquals(hopEntity.getLocationName(), hopTest.getLocationName());
        //assertEquals(hopEntity.getLocationCoordinates(), hopTest.getLocationCoordinates());
        assertEquals(hopEntity.getProcessingDelayMins(), hopTest.getProcessingDelayMins());

    }
}
