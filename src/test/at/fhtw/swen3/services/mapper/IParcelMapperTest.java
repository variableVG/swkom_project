package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.persistence.NewParcelInfo;
import at.fhtw.swen3.persistence.Parcel;
import at.fhtw.swen3.persistence.Recipient;
import at.fhtw.swen3.persistence.TrackingInformation;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.ParcelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {OpenApiGeneratorApplication.class})
public class IParcelMapperTest {
    ParcelEntity parcelEntity;


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

    }


    void parcelEntityToParcelDtoTest() {

        ParcelDTO parcelDTO = IParcelMapper.INSTANCE.parcelEntityToParcelDto(parcelEntity);
        //IParcelMapper gives back already a JSON!
        assertThat(parcelDTO.getWeight()).isEqualTo(parcelEntity.getParcel().getWeight());
        assertThat(parcelDTO.getRecipient()).isEqualTo(parcelEntity.getParcel().getRecipient());
        assertThat(parcelDTO.getSender()).isEqualTo(parcelEntity.getParcel().getSender());
    }

    @Test
    void parcelDtoToParcelEntityTest() {
        ParcelDTO parcelDTO = new ParcelDTO();

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


        parcelDTO.setSender(sender);
        parcelDTO.setRecipient(recipient);



        assertThat(parcelEntity.getParcel().getRecipient()).isEqualTo(parcelDTO.getRecipient());
        assertThat(parcelEntity.getParcel().getSender()).isEqualTo(parcelDTO.getSender());



    }

}
