package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@SpringBootTest
public class ParcelMapperTest {

    ParcelEntity parcelEntity;
    Parcel parcelDTO;
    NewParcelInfo newParcelInfoDTO;
    TrackingInformation trackingInformationDTO;

    @BeforeEach
    void setUp() {
        parcelEntity = new ParcelEntity();
        //Create recipient
        RecipientEntity recipient = RecipientEntity.builder()
                .name("Violeta")
                .street("Straße A")
                .postalCode("1200")
                .city("Vienna")
                .country("Austria").build();
        //Create sender
        RecipientEntity sender = RecipientEntity.builder()
                .name("Georg")
                .street("Straße B")
                .postalCode("3000")
                .city("Linz")
                .country("Austria").build();
        //Create Parcel
        /*
        ParcelModelEntity parcelModelEntity = ParcelModelEntity.builder()
                .weight(23.5F)
                .sender(sender)
                .recipient(recipient).build();

         */
        //Create NewParcelinfo
        /*
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();
        newParcelInfoEntity.setTrackingId("PYJRB4HZ6");

         */
        //Create TrackingInformation
        /*
        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity();
        trackingInformationEntity.setState(TrackingInformationEntity.StateEnum.fromValue("Pickup"));

         */

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
        /*
        trackingInformationEntity.setVisitedHops(visitedHops);
        trackingInformationEntity.setFutureHops(futureHops);
        */

        //Add Parcel, NewParcelInfo, TrackingInformation
        parcelEntity.setWeight(34.6F);
        parcelEntity.setSender(sender);
        parcelEntity.setRecipient(recipient);
        parcelEntity.setVisitedHops(visitedHops);
        parcelEntity.setFutureHops(futureHops);
        parcelEntity.setState(ParcelEntity.StateEnum.fromValue("Pickup"));

        Recipient recipientDto = Recipient.builder()
                .name("Violeta")
                .street("Straße A")
                .postalCode("1200")
                .city("Vienna")
                .country("Austria").build();
        Recipient senderDto= Recipient.builder()
                .name("Georg")
                .street("Straße B")
                .postalCode("3000")
                .city("Linz")
                .country("Austria").build();

        //Create ParcelDTO
        parcelDTO = Parcel.builder()
                .weight(56.8F)
                .recipient(recipientDto)
                .sender(senderDto)
                .build();

        //Create ParcelInfoDTO
        newParcelInfoDTO = new NewParcelInfo("PYJRB4HZ6");
        //newParcelInfoDTO.setTrackingId("PYJRB4HZ6");

        //Create trackingInformationDTO
        trackingInformationDTO = new TrackingInformation();
        trackingInformationDTO.setState(TrackingInformation.StateEnum.fromValue("Pickup"));
        //TODO complete trackingInformataion DTO about visitedLoops and futureLoops

    }

    @Test
    void parcelEntityToParcelDtoTest() {
        log.info("TEST parcelEntityToParcelDtoTest in ParcelMapperTest");

        //Run Mapper
        Parcel parcelDTOTest = ParcelMapper.INSTANCE.parcelEntityToParcelDto(parcelEntity);
        //IParcelMapper gives back already a JSON!

        //Test
        assertThat(parcelDTOTest.getWeight()).isEqualTo(parcelEntity.getWeight());
        assertThat(parcelDTOTest.getRecipient().getName()).isEqualTo(parcelEntity.getRecipient().getName());
        assertThat(parcelDTOTest.getSender().getName()).isEqualTo(parcelEntity.getSender().getName());
        assertThat(parcelDTOTest.getRecipient().getCity()).isEqualTo(parcelEntity.getRecipient().getCity());
        assertThat(parcelDTOTest.getSender().getCity()).isEqualTo(parcelEntity.getSender().getCity());
        assertThat(parcelDTOTest.getRecipient().getCountry()).isEqualTo(parcelEntity.getRecipient().getCountry());
        assertThat(parcelDTOTest.getSender().getCountry()).isEqualTo(parcelEntity.getSender().getCountry());
        assertThat(parcelDTOTest.getRecipient().getPostalCode()).isEqualTo(parcelEntity.getRecipient().getPostalCode());
        assertThat(parcelDTOTest.getSender().getPostalCode()).isEqualTo(parcelEntity.getSender().getPostalCode());
    }

    @Test
    void parcelDtoToParcelEntityTest() {
        log.info("TEST parcelDtoToParcelEntityTest in ParcelMapperTest");
        //Run Mapper
        ParcelEntity parcelEntityTest = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcelDTO);

        //Test
        assertThat(parcelEntityTest.getRecipient().getName()).isEqualTo(parcelDTO.getRecipient().getName());
        assertThat(parcelEntityTest.getSender().getName()).isEqualTo(parcelDTO.getSender().getName());
        assertThat(parcelEntityTest.getWeight()).isEqualTo(parcelDTO.getWeight());
    }

    /*@Test
    void dtoToToParcelEntityTest() {
        //Run Mapper
        ParcelEntity parcelEntityTest = ParcelMapper.INSTANCE.dtoToToParcelEntity(parcelDTO, newParcelInfoDTO, trackingInformationDTO);

        assertThat(parcelEntityTest.getRecipient().getName()).isEqualTo(parcelDTO.getRecipient().getName());
        assertThat(parcelEntityTest.getSender().getName()).isEqualTo(parcelDTO.getSender().getName());
        assertThat(parcelEntityTest.getWeight()).isEqualTo(parcelDTO.getWeight());
        assertThat(parcelEntityTest.getTrackingId()).isEqualTo(newParcelInfoDTO.getTrackingId());
        assertEquals(parcelEntityTest.getState().name(), trackingInformationDTO.getState().name());

    }*/

    @Test
    void parcelEntityToNewParcelInfoDtoTest() {
        log.info("TEST parcelEntityToNewParcelInfoDtoTest in ParcelMapperTest");
        NewParcelInfo newParcelInfoDto = ParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(parcelEntity);

        assertEquals(newParcelInfoDto.getTrackingId(), parcelEntity.getTrackingId());
    }

    @Test
    void parcelEntityToTrackingInformationDtoTest() {
        log.info("TEST parcelEntityToTrackingInformationDtoTest in ParcelMapperTest");
        TrackingInformation trackingInformationTest = ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);

        //Check state
        assertEquals(trackingInformationTest.getState().name(), parcelEntity.getState().name());

        //Check future hoops
        assertEquals(trackingInformationTest.getFutureHops().size(), parcelEntity.getFutureHops().size());

        //Check visited hoops
        assertEquals(trackingInformationTest.getVisitedHops().size(), parcelEntity.getVisitedHops().size());

    }

    @Test
    void newParcelInfoDtoToParcelEntityTest() {
        log.info("TEST newParcelInfoDtoToParcelEntityTest in ParcelMapperTest");
        NewParcelInfo newParcelInfoTest = new NewParcelInfo("BYJRB4HZ6");

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.newParcelInfoDtoToParcelEntity(newParcelInfoTest);

        assertEquals(newParcelInfoTest.getTrackingId(), parcelEntity.getTrackingId());

    }

}
