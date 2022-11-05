package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParcelImplTest {

    @Autowired
    private ParcelService parcelImpl;

    @Test
    void submitParcelTest() {
        //Create recipient
        Recipient recipient = Recipient.builder()
                .name("Violeta")
                .street("Straße A")
                .postalCode("1200")
                .city("Vienna")
                .country("Austria").build();
        System.out.println(recipient.getName());
        //Create sender
        Recipient sender = Recipient.builder()
                .name("Ibo")
                .street("Straße B")
                .postalCode("3000")
                .city("Frankfurt")
                .country("Germany").build();
        System.out.println(sender.getName());
        Parcel parcel = Parcel.builder()
                .sender(sender).recipient(recipient).weight(23.5F)
                .build();

        System.out.println(parcel.getWeight());


        NewParcelInfo newparcelInfo = parcelImpl.submitParcel(parcel);

        System.out.println(newparcelInfo.getTrackingId());


    }
}