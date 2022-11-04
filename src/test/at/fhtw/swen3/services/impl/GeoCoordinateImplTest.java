package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repository.GeoCoordinateRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class GeoCoordinateImplTest {

    @Autowired
    GeoCoordinateImpl geoCoordinateImpl;

    @BeforeEach
    void setUp(){
        geoCoordinateImpl = new GeoCoordinateImpl();
    }

    @Test
    void submitGeoCoordinate() {
        geoCoordinateImpl.submitGeoCoordinate(8.0, 9.0);
    }
}