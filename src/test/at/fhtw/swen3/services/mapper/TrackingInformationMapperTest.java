package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.TrackingInformationEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class TrackingInformationMapperTest {
    TrackingInformationEntity trackingInformationEntity;
    TrackingInformation trackingInformationDto;

    @BeforeEach
    void setUp(){


        trackingInformationDto = new TrackingInformation();
        trackingInformationDto.setState(TrackingInformation.StateEnum.PICKUP);

        trackingInformationEntity = new TrackingInformationEntity();
        trackingInformationEntity.setState(TrackingInformationEntity.StateEnum.PICKUP);

    }

    @Test
    void dtoToEntityTest(){
        /*
        TrackingInformationEntity trackingInformationEntityTest = TrackingInformationMapper.INSTANCE.dtoToEntity(trackingInformationDto);


        assertEquals(trackingInformationDto.getState(), trackingInformationEntityTest.getState());

         */
    }
    @Test
    void entityToDtoTest(){
        /*
        TrackingInformation trackingInformationTest = TrackingInformationMapper.INSTANCE.entityToDto(trackingInformationEntity);
        assertEquals(trackingInformationEntity.getState(), trackingInformationTest.getState());

         */

    }
}
