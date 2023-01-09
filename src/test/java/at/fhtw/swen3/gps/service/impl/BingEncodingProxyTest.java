package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BingEncodingProxyTest {

    GeoEncodingService geoEncodingService;

    @Test
    void encodeAddress() {
        geoEncodingService = new BingEncodingProxy();

        RecipientEntity recipient = RecipientEntity.builder().name("Violeta").street("Pfeilgasse")
                .postalCode("A-1080").city("Vienna").country("Austria").build();

        // I already looked for those coordinates:
        GeoCoordinateEntity coordinates = GeoCoordinateEntity.builder().lat(48.2082221).lon(16.3478833).build();

        GeoCoordinateEntity coordinatesTest = geoEncodingService.encodeAddress(recipient);
        System.out.println("Lat " + coordinatesTest.getLat());
        System.out.println("Lon " + coordinatesTest.getLon());
        assertNotNull(coordinatesTest);
        assertEquals(coordinatesTest.getLat(), coordinates.getLat());
        assertEquals(coordinatesTest.getLon(), coordinates.getLon());

    }
}