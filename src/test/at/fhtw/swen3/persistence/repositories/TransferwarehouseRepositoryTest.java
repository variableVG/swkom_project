package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entity.*;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TransferwarehouseRepositoryTest {

    @Autowired
    TransferwarehouseRepository repo;
    @Autowired
    private HopRepository hopRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;

    TransferwarehouseEntity transferwarehouseEntity;

    @Test
    void setUp() {

        GeoCoordinateEntity geoCoordinate = GeoCoordinateEntity.builder().lat(23.5).lon(56.9).build();
        GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(geoCoordinate);
        HopEntity hop = new HopEntity();
        hop.setCode("VIGG59"); hop.setDescription("Description of Hop");
        hop.setProcessingDelayMins(3); hop.setLocationName("Vienna");
        hop.setLocationCoordinates(newGeoCoordinateEntity); hop.setHopType("R");

        HopEntity newHop = hopRepository.save(hop);

        transferwarehouseEntity = new TransferwarehouseEntity();
        transferwarehouseEntity.setLogisticsPartner("logistics Partner");
        transferwarehouseEntity.setRegionGeoJson("Vienna");
        transferwarehouseEntity.setLogisticsPartnerUrl("logistics Partner url");


    }

    @Test
    void saveTest() {
/*
        TransferwarehouseEntity TransferwarehouseRepositoryTest = repo.save(transferwarehouseEntity);
        assertNotNull(TransferwarehouseRepositoryTest.getId());

*/
    }


}