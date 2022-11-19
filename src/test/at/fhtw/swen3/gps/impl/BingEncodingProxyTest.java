package at.fhtw.swen3.gps.impl;

import at.fhtw.swen3.gps.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BingEncodingProxyTest {


    GeoEncodingService geoEncodingService;

    @Test
    void encodeAddressTest() {
        geoEncodingService = new BingEncodingProxy();

        RecipientEntity recipient = RecipientEntity.builder().name("Violeta").street("16%20Lambrechtgasse")
                .postalCode("A-1040").city("Vienna").country("Austria").build();

        try {
            geoEncodingService.encodeAddress(recipient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}