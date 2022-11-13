package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ParcelImplTest {

    @Autowired
    private ParcelServiceImpl parcelImpl;

    @Test
    void submitParcelTest() {

        //1) PREPARE TEST
        //Create recipient
        Recipient recipient = Recipient.builder().name("Violeta").street("Straße A")
                .postalCode("A-1200").city("Vienna").country("Austria").build();

        //Create sender
        Recipient sender = Recipient.builder().name("Ibo").street("Straße B")
                .postalCode("A-1200").city("Vienna").country("Austria").build();

        Parcel parcel = Parcel.builder().sender(sender).recipient(recipient).weight(23.5F)
                .build();


        //2) TEST FUNCTION
        NewParcelInfo newparcelInfo = parcelImpl.submitParcel(parcel);

        // 3) CHECK RESULTS
        //System.out.println("Generated Id is " + newparcelInfo.getTrackingId());
        assertNotNull(newparcelInfo.getTrackingId());


    }

}