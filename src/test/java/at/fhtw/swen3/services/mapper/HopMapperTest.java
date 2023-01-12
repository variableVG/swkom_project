package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class HopMapperTest {
    HopEntity hopEntity;
    Hop hopDto;

    @BeforeEach
    void setUp(){
        hopDto = new Hop();
        hopEntity = new HopEntity();

        hopDto.setHopType("FirstHoptype");
        hopDto.setCode("RM123");
        hopDto.setDescription("fast123");
        hopDto.setProcessingDelayMins(23);
        hopDto.setLocationName("Homs");

    }

    @Test
    void dtoToEntityTest(){
        log.info("TEST dtoToEntityTest in HopMapperTest");
        HopEntity hopEntityTest = HopMapper.INSTANCE.dtoToEntity(hopDto);
        assertEquals(hopDto.getHopType(), hopEntityTest.getHopType());
        assertEquals(hopDto.getCode(), hopEntityTest.getCode());
        assertEquals(hopDto.getDescription(), hopEntityTest.getDescription());
        assertEquals(hopDto.getLocationName(), hopEntityTest.getLocationName());
        assertEquals(hopDto.getProcessingDelayMins(), hopEntityTest.getProcessingDelayMins());


    }

    @Test
    void entityToDtoTest(){
        log.info("TEST entityToDtoTest in HopMapperTest");
        Hop hopTest = HopMapper.INSTANCE.entityToDto(hopEntity);
        assertEquals(hopEntity.getHopType(), hopTest.getHopType());
        assertEquals(hopEntity.getCode(), hopTest.getCode());
        assertEquals(hopEntity.getDescription(), hopTest.getDescription());
        assertEquals(hopEntity.getLocationName(), hopTest.getLocationName());
        assertEquals(hopEntity.getProcessingDelayMins(), hopTest.getProcessingDelayMins());

    }
}
