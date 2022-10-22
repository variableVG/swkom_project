package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.persistence.entity.*;
import at.fhtw.swen3.services.dto.*;
import org.hibernate.type.descriptor.java.OffsetDateTimeJavaDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IParcelMapperTest {

    ParcelEntity parcelEntity;
    Parcel parcelDTO;
    NewParcelInfo newParcelInfoDTO;
    TrackingInformation trackingInformationDTO;

    @BeforeEach
    void setUp() {
        parcelEntity = new ParcelEntity();
        //Create recipient
        Recipient recipient = Recipient.builder()
                .name("Violeta")
                .street("Straße A")
                .postalCode("1200")
                .city("Vienna")
                .country("Austria").build();
        //Create sender
        Recipient sender = Recipient.builder()
                .name("Georg")
                .street("Straße B")
                .postalCode("3000")
                .city("Linz")
                .country("Austria").build();
        //Create Parcel
        ParcelModelEntity parcelModelEntity = ParcelModelEntity.builder()
                .weight(23.5F)
                .sender(sender)
                .recipient(recipient).build();
        //Create NewParcelinfo
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();
        newParcelInfoEntity.setTrackingId("PYJRB4HZ6");
        //Create TrackingInformation
        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity();
        trackingInformationEntity.setState(TrackingInformationEntity.StateEnum.fromValue("Pickup"));

        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        List<HopArrivalEntity> futureHops = new ArrayList<>();
        HopArrivalEntity hopArrivalEntity1 = HopArrivalEntity.builder()
                                            .code("XWZQ5").description("description 1")
                                            .dateTime(OffsetDateTime.parse("2022-10-22T12:57:59.601Z")).build();
        HopArrivalEntity hopArrivalEntity2 = HopArrivalEntity.builder()
                .code("PXXS27").description("description 2")
                .dateTime(OffsetDateTime.parse("2022-10-22T12:57:59.601Z")).build();

        visitedHops.add(hopArrivalEntity1);
        futureHops.add(hopArrivalEntity2);

        trackingInformationEntity.setVisitedHops(visitedHops);
        trackingInformationEntity.setFutureHops(futureHops);



        //Add Parcel, NewParcelInfo, TrackingInformation
        parcelEntity.setParcelModelEntity(parcelModelEntity);
        parcelEntity.setNewParcelInfoEntity(newParcelInfoEntity);
        parcelEntity.setTrackingInformationEntity(trackingInformationEntity);


        //Create ParcelDTO
        parcelDTO = Parcel.builder()
                .weight(56.8F)
                .recipient(recipient)
                .sender(sender)
                .build();

        //Create ParcelInfoDTO
        newParcelInfoDTO = new NewParcelInfo("PYJRB4HZ6");
        //newParcelInfoDTO.setTrackingId("PYJRB4HZ6");

        //Create trackingInformationDTO
        trackingInformationDTO = new TrackingInformation();
        trackingInformationDTO.setState(TrackingInformation.StateEnum.fromValue("Pickup"));
        //TODO complete trackingInformataion DTO about visitedLoops and futureLoops

        Recipient a = parcelEntity.getParcelModelEntity().getRecipient();

    }

    @Test
    void parcelEntityToParcelDtoTest() {

        //Run Mapper
        Parcel parcelDTOTest = IParcelMapper.INSTANCE.parcelEntityToParcelDto(parcelEntity);
        //IParcelMapper gives back already a JSON!

        //Test
        assertThat(parcelDTOTest.getWeight()).isEqualTo(parcelEntity.getParcelModelEntity().getWeight());
        assertThat(parcelDTOTest.getRecipient()).isEqualTo(parcelEntity.getParcelModelEntity().getRecipient());
        assertThat(parcelDTOTest.getSender()).isEqualTo(parcelEntity.getParcelModelEntity().getSender());
    }

    @Test
    void parcelDtoToParcelEntityTest() {
        //Run Mapper
        ParcelEntity parcelEntityTest = IParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcelDTO);

        //Test
        assertThat(parcelEntityTest.getParcelModelEntity().getRecipient()).isEqualTo(parcelDTO.getRecipient());
        assertThat(parcelEntityTest.getParcelModelEntity().getSender()).isEqualTo(parcelDTO.getSender());
        assertThat(parcelEntityTest.getParcelModelEntity().getWeight()).isEqualTo(parcelDTO.getWeight());
    }

    @Test
    void dtoToToParcelEntityTest() {
        //Run Mapper
        ParcelEntity parcelEntityTest = IParcelMapper.INSTANCE.dtoToToParcelEntity(parcelDTO, newParcelInfoDTO, trackingInformationDTO);

        assertThat(parcelEntityTest.getParcelModelEntity().getRecipient()).isEqualTo(parcelDTO.getRecipient());
        assertThat(parcelEntityTest.getParcelModelEntity().getSender()).isEqualTo(parcelDTO.getSender());
        assertThat(parcelEntityTest.getParcelModelEntity().getWeight()).isEqualTo(parcelDTO.getWeight());
        assertThat(parcelEntityTest.getNewParcelInfoEntity().getTrackingId()).isEqualTo(newParcelInfoDTO.getTrackingId());
        assertEquals(parcelEntityTest.getTrackingInformationEntity().getState().name(), trackingInformationDTO.getState().name());

    }

    @Test
    void parcelEntityToNewParcelInfoDtoTest() {
        NewParcelInfo newParcelInfoDto = IParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(parcelEntity);

        assertEquals(newParcelInfoDto.getTrackingId(), parcelEntity.getNewParcelInfoEntity().getTrackingId());
    }

    @Test
    void parcelEntityToTrackingInformationDtoTest() {
        TrackingInformation trackingInformation = IParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);

        //Check state
        assertEquals(trackingInformation.getState().name(), parcelEntity.getTrackingInformationEntity().getState().name());

        //Check future hoops
        assertEquals(trackingInformation.getFutureHops().size(), parcelEntity.getTrackingInformationEntity().getFutureHops().size());

        //Check visited hoops
        assertEquals(trackingInformation.getVisitedHops().size(), parcelEntity.getTrackingInformationEntity().getVisitedHops().size());

    }

    @Test
    void newParcelInfoDtoToParcelEntityTest() {
        NewParcelInfo newParcelInfo = new NewParcelInfo("BYJRB4HZ6");

        ParcelEntity parcelEntity = IParcelMapper.INSTANCE.newParcelInfoDtoToParcelEntity(newParcelInfo);

        assertEquals(newParcelInfo.getTrackingId(), parcelEntity.getNewParcelInfoEntity().getTrackingId());

    }

}
