package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.persistence.NewParcelInfo;
import at.fhtw.swen3.persistence.Parcel;
import at.fhtw.swen3.persistence.Recipient;
import at.fhtw.swen3.persistence.TrackingInformation;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfoDTO;
import at.fhtw.swen3.services.dto.ParcelDTO;
import at.fhtw.swen3.services.dto.TrackingInformationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {OpenApiGeneratorApplication.class})
public class IParcelMapperTest {

    ParcelEntity parcelEntity;
    ParcelDTO parcelDTO;
    NewParcelInfoDTO newParcelInfoDTO;
    TrackingInformationDTO trackingInformationDTO;

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
        Parcel parcel = Parcel.builder()
                .weight(23.5F)
                .sender(sender)
                .recipient(recipient).build();
        //Create NewParcelinfo
        NewParcelInfo newParcelInfo = new NewParcelInfo();
        newParcelInfo.setTrackingId("PYJRB4HZ6");

        //Add Parcel, NewParcelInfo, TrackingInformation
        parcelEntity.setParcel(parcel);
        parcelEntity.setNewParcelInfo(newParcelInfo);
        //TODO add TrackingInformation instance to the parcelEntity.

        //Create ParcelDTO
        parcelDTO = new ParcelDTO();
        parcelDTO.setSender(sender);
        parcelDTO.setRecipient(recipient);
        parcelDTO.setWeight(56.8F);

        //Create ParcelInfoDTO
        newParcelInfoDTO = new NewParcelInfoDTO();
        newParcelInfoDTO.setTrackingId("PYJRB4HZ6");

        //Create trackingInformationDTO
        trackingInformationDTO = new TrackingInformationDTO();

    }


    @Test
    void parcelEntityToParcelDtoTest() {

        //Run Mapper
        ParcelDTO parcelDTOTest = IParcelMapper.INSTANCE.parcelEntityToParcelDto(parcelEntity);
        //IParcelMapper gives back already a JSON!

        //Test
        assertThat(parcelDTOTest.getWeight()).isEqualTo(parcelEntity.getParcel().getWeight());
        assertThat(parcelDTOTest.getRecipient()).isEqualTo(parcelEntity.getParcel().getRecipient());
        assertThat(parcelDTOTest.getSender()).isEqualTo(parcelEntity.getParcel().getSender());
    }

    @Test
    void parcelDtoToParcelEntityTest() {
        //Run Mapper
        ParcelEntity parcelEntityTest = IParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcelDTO);

        //Test
        assertThat(parcelEntityTest.getParcel().getRecipient()).isEqualTo(parcelDTO.getRecipient());
        assertThat(parcelEntityTest.getParcel().getSender()).isEqualTo(parcelDTO.getSender());
        assertThat(parcelEntityTest.getParcel().getWeight()).isEqualTo(parcelDTO.getWeight());

    }

    @Test
    void dtoToToParcelEntityTest() {

        //Run Mapper
        ParcelEntity parcelEntityTest = IParcelMapper.INSTANCE.dtoToToParcelEntity(parcelDTO, newParcelInfoDTO, trackingInformationDTO);

        assertThat(parcelEntityTest.getParcel().getRecipient()).isEqualTo(parcelDTO.getRecipient());
        assertThat(parcelEntityTest.getParcel().getSender()).isEqualTo(parcelDTO.getSender());
        assertThat(parcelEntityTest.getParcel().getWeight()).isEqualTo(parcelDTO.getWeight());
        assertThat(parcelEntityTest.getNewParcelInfo().getTrackingId()).isEqualTo(newParcelInfoDTO.getTrackingId());


    }

}
