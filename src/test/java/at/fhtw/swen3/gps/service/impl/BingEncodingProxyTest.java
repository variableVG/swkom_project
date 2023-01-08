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

        RecipientEntity recipient = RecipientEntity.builder().name("Violeta").street("16%20Lambrechtgasse")
                .postalCode("A-1040").city("Vienna").country("Austria").build();

        // I already looked for those coordinates:
        GeoCoordinateEntity coordinates = GeoCoordinateEntity.builder().lat(48.1910153).lon(16.3640983).build();

        GeoCoordinateEntity coordinatesTest = geoEncodingService.encodeAddress(recipient);
        assertNotNull(coordinatesTest);
        assertEquals(coordinatesTest.getLat(), coordinates.getLat());
        assertEquals(coordinatesTest.getLon(), coordinates.getLon());

    }
}