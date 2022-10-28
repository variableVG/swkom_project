package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HopArrivalMapperTest {


    @Test
    void dtoToEntityTest() {

        HopArrival hopArrivalDto = HopArrival.builder()
                .code("PXXS27").description("description 2")
                .dateTime(OffsetDateTime.parse("2022-10-22T12:57:59.601Z")).build();

        HopArrivalEntity hopArrivalEntity = HopArrivalMapper.INSTANCE.dtoToEntity(hopArrivalDto);

        assertEquals(hopArrivalDto.getCode(), hopArrivalEntity.getCode());
        assertEquals(hopArrivalDto.getDescription(), hopArrivalEntity.getDescription());
        assertEquals(hopArrivalDto.getDateTime(), hopArrivalEntity.getDateTime());

    }

    @Test
    void entityToDtoTest() {
        HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder()
                .code("XWZQ5").description("description 1")
                .dateTime(OffsetDateTime.parse("2022-10-22T12:57:59.601Z")).build();

        HopArrival hopArrivalDto = HopArrivalMapper.INSTANCE.entityToDto(hopArrivalEntity);

        assertEquals(hopArrivalDto.getCode(), hopArrivalEntity.getCode());
        assertEquals(hopArrivalDto.getDescription(), hopArrivalEntity.getDescription());
        assertEquals(hopArrivalDto.getDateTime(), hopArrivalEntity.getDateTime());

    }


}
