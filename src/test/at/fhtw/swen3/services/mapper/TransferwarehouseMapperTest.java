package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransferwarehouseMapperTest {

    @Test
    void dtoToEntityTest(){
        Transferwarehouse transferwarehouseDto = Transferwarehouse.builder()
                .logisticsPartner("logistics Partner")
                .logisticsPartnerUrl("logistics Partner url")
                .regionGeoJson("Vienna?").build();

        TransferwarehouseEntity transferwarehouseEntityTest = TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouseDto);

        assertEquals(transferwarehouseEntityTest.getLogisticsPartner(), transferwarehouseDto.getLogisticsPartner());
        assertEquals(transferwarehouseEntityTest.getLogisticsPartnerUrl(), transferwarehouseDto.getLogisticsPartnerUrl());
        assertEquals(transferwarehouseEntityTest.getRegionGeoJson(), transferwarehouseDto.getRegionGeoJson());
    }
    @Test
    void entityToDtoTest(){
        TransferwarehouseEntity transferwarehouseEntity = TransferwarehouseEntity.builder()
                .logisticsPartner("logistics Partner")
                .logisticsPartnerUrl("logistics Partner url")
                .regionGeoJson("Vienna?").build();

        Transferwarehouse transferwarehouseDtoTest = TransferwarehouseMapper.INSTANCE.entityToDto(transferwarehouseEntity);

        assertEquals(transferwarehouseEntity.getLogisticsPartner(), transferwarehouseDtoTest.getLogisticsPartner());
        assertEquals(transferwarehouseEntity.getLogisticsPartnerUrl(), transferwarehouseDtoTest.getLogisticsPartnerUrl());
        assertEquals(transferwarehouseEntity.getRegionGeoJson(), transferwarehouseDtoTest.getRegionGeoJson());
    }


}
