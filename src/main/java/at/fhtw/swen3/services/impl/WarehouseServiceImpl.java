package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.persistence.repositories.GeoCoordinateRepository;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.GeoCoordinateMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseNextHopsRepository warehouseNextHopsRepository;
    @Autowired
    private HopRepository hopRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    private MyValidator myValidator;

    private WarehouseEntity setCorrectHopTypes(WarehouseEntity warehouse) {
        warehouse.setHopType("warehouse");
        ArrayList<String> allowedTypes = new ArrayList<String>(){{
            add("hop");
            add("transferwarehouse");
            add("truck");
            add("warehouse");
        }};
        for(WarehouseNextHopsEntity nextHop : warehouse.getNextHops()) {
            String nextHopType = nextHop.getHop().getHopType();
            if(!allowedTypes.contains(nextHopType)) {
                nextHop.getHop().setHopType("hop");
            }
        }
        return warehouse;
    }

    @Override
    public void importWarehouses(Warehouse warehouse) throws Exception {
        //Map dto to Entity
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        //TODO Fix Map error
        // Map did not work properly for the attributes that warehouse inherits from HopEntity
        warehouseEntity.setCode(warehouse.getCode());
        warehouseEntity.setDescription(warehouse.getDescription());
        warehouseEntity.setHopType(warehouse.getHopType());
        warehouseEntity.setLocationName(warehouse.getLocationName());
        warehouseEntity.setProcessingDelayMins(warehouse.getProcessingDelayMins());
        warehouseEntity.setLocationCoordinates(GeoCoordinateMapper.INSTANCE.dtoToEntity((warehouse.getLocationCoordinates())));

        warehouseEntity = setCorrectHopTypes(warehouseEntity);

        //Save Warehouse
        try {
            //first store each of the hops with their entities in the list warehouseNextHops
            for(WarehouseNextHopsEntity nextHop : warehouseEntity.getNextHops()) {
                GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(nextHop.getHop().getLocationCoordinates());
                nextHop.getHop().setLocationCoordinates(newGeoCoordinateEntity);

                HopEntity newHopEntity = hopRepository.save(nextHop.getHop());
                nextHop.setHop(newHopEntity);

                //We also need to assign a warehouse to the warehouseNextHops
                nextHop.setWarehouse(warehouseEntity);
            }

            // Store coordinates from warehouse itself (inherits from HopEntity)
            GeoCoordinateEntity newGeoCoordinateEntity = geoCoordinateRepository.save(warehouseEntity.getLocationCoordinates());
            warehouseEntity.setLocationCoordinates(newGeoCoordinateEntity);

            WarehouseEntity newWarehouseEntity = warehouseRepository.save(warehouseEntity);

        } catch (Exception e){
            // https://www.javacodegeeks.com/10-best-practices-to-handle-java-exceptions.html
            throw new Exception("Failed to store Warehouse: ", e);
        }
    }
}
