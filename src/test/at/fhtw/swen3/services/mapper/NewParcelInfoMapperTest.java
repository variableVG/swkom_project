package at.fhtw.swen3.services.mapper;
import at.fhtw.swen3.persistence.entity.ErrorEntity;
import at.fhtw.swen3.persistence.entity.NewParcelInfoEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NewParcelInfoMapperTest {
    NewParcelInfoEntity newParcelInfoEntity;
    NewParcelInfo newParcelInfoDto;

    @BeforeEach
    void setUp(){
        newParcelInfoDto = new NewParcelInfo();
        newParcelInfoDto.setTrackingId("5139634");
        newParcelInfoEntity = new NewParcelInfoEntity();

    }

    @Test
    void dtoToEntityTest(){

        NewParcelInfoEntity newParcelInfoEntityTest =  NewParcelInfoMapper.INSTANCE.dtoToEntity(newParcelInfoDto);
        assertEquals(newParcelInfoDto.getTrackingId(), newParcelInfoEntityTest.getTrackingId());

    }
    @Test
    void entityToDtoTest(){

        NewParcelInfo newParcelInfoTest =  NewParcelInfoMapper.INSTANCE.entityToDto(newParcelInfoEntity);
        assertEquals(newParcelInfoEntity.getTrackingId(), newParcelInfoTest.getTrackingId());

    }
}
