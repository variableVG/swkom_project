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

        //1) PREPARE TEST
        //Create recipient
        Recipient recipient = Recipient.builder().name("Violeta").street("Straße A")
                .postalCode("A-1200").city("Vienna").country("Austria").build();

        //Create sender
        Recipient sender = Recipient.builder().name("Ibo").street("Straße B")
                .postalCode("G-3000").city("Frankfurt").country("Germany").build();

        Parcel parcel = Parcel.builder().sender(sender).recipient(recipient).weight(23.5F)
                .build();



        //2) TEST FUNCTION
        NewParcelInfo newparcelInfo = parcelImpl.submitParcel(parcel);

        // 3) CHECK RESULTS
        //System.out.println("Generated Id is " + newparcelInfo.getTrackingId());
        assertNotNull(newparcelInfo.getTrackingId());


    }

}