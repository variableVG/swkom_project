package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@SpringBootTest
public class TruckMapperTest {
    @Test
    void dtoToEntityTest(){
        log.info("TEST dtoToEntityTest in TruckMapperTest");
        Truck truckDto = Truck.builder()
                .regionGeoJson("region Geo Json")
                .numberPlate("XY 123AB").code("ABC").hopType("truck").description("This is a test")
                .build();

        log.info("TruckDto created with number plate " + truckDto.getNumberPlate());

        TruckEntity truckEntityTest = TruckMapper.INSTANCE.dtoToEntity(truckDto);

        log.info("TruckEntity created with number plate " + truckDto.getNumberPlate());

        assertEquals(truckEntityTest.getNumberPlate(), truckDto.getNumberPlate());
        assertEquals(truckEntityTest.getRegionGeoJson(), truckDto.getRegionGeoJson());
    }


    @Test
    void entityToDtoTest(){
        log.info("TEST entityToDtoTest in TruckMapperTest");
        TruckEntity truckEntity = TruckEntity.builder()
                .regionGeoJson("region Geo Json for Entity")
                .numberPlate("XY 123AC")
                .build();

        Truck truckDtoTest= TruckMapper.INSTANCE.entityToDto(truckEntity);

        assertEquals(truckDtoTest.getNumberPlate(), truckEntity.getNumberPlate());
        assertEquals(truckDtoTest.getRegionGeoJson(), truckEntity.getRegionGeoJson());
    }

}
