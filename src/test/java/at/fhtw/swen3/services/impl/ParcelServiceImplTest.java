package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ParcelServiceImplTest {

    @Autowired
    private ParcelServiceImpl parcelImpl;

    @Test
    void submitParcelTest()  {

        /*

        //1) PREPARE TEST
        //Create recipient
        Recipient recipient = Recipient.builder().name("Violeta").street("Pfeilgasse")
                .postalCode("A-1080").city("Vienna").country("Austria").build();

        //Create sender
        Recipient sender = Recipient.builder().name("Ibo").street("Lambrechtgasse")
                .postalCode("A-1040").city("Vienna").country("Austria").build();

        Parcel parcel = Parcel.builder().sender(sender).recipient(recipient).weight(23.5F)
                .build();
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);

        //2) TEST FUNCTION
        NewParcelInfo newparcelInfo = null;
        try {
            newparcelInfo = parcelImpl.submitParcel(parcelEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 3) CHECK RESULTS
        System.out.println("Generated Id is " + newparcelInfo.getTrackingId());
        assertNotNull(newparcelInfo.getTrackingId());

         */


    }

    @Test
    void reportParcelDeliveryTest(){


    }

    @Test
    void predictFutureHops() {

        GeoCoordinateEntity sender = GeoCoordinateEntity.builder().lat(48.1898128).lon(16.3656104).build();
        GeoCoordinateEntity recipient = GeoCoordinateEntity.builder().lat(48.233571).lon(16.388961).build();
        parcelImpl.predictFutureHops(sender, recipient);

    }

    @Test
    void findNearestHop() {
        HopEntity hop = parcelImpl.findNearestHop(48.1898128, 16.3656104);
        System.out.println("Hop is " + hop.getId());
    }


}