package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.persistence.entity.NewParcelInfoEntity;
import at.fhtw.swen3.persistence.entity.ParcelModelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static at.fhtw.swen3.services.dto.TrackingInformation.StateEnum.PICKUP;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {OpenApiGeneratorApplication.class})
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

        //Add Parcel, NewParcelInfo, TrackingInformation
        parcelEntity.setParcelModelEntity(parcelModelEntity);
        parcelEntity.setNewParcelInfoEntity(newParcelInfoEntity);
        //TODO add TrackingInformation instance to the parcelEntity.

        //Create ParcelDTO
        parcelDTO = Parcel.builder()
                .weight(56.8F)
                .recipient(recipient)
                .sender(sender)
                .build();

        //Create ParcelInfoDTO
        newParcelInfoDTO = new NewParcelInfo();
        newParcelInfoDTO.setTrackingId("PYJRB4HZ6");

        //Create trackingInformationDTO
        trackingInformationDTO = new TrackingInformation();
        trackingInformationDTO.setState(PICKUP);
        //TODO complete trackingInformataion DTO about visitedLoops and futureLoops

        Recipient a = parcelEntity.getParcelModelEntity().getRecipient();
        System.out.println("Recipient name is " + a.getName());

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
        assertThat(parcelEntityTest.getTrackingInformationEntity().getState()).isEqualTo(trackingInformationDTO.getState());


    }

}
