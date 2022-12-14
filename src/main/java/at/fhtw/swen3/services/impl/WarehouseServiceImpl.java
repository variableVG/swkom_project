package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.mapper.GeoCoordinateMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private TruckRepository truckRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    private MyValidator myValidator;

    private void resetDB() {
        warehouseNextHopsRepository.deleteAll();
        warehouseRepository.deleteAll();
        hopRepository.deleteAll();
        geoCoordinateRepository.deleteAll();
    }

    private WarehouseEntity saveWarehouse(WarehouseEntity warehouse) throws BLException {
        //Validation:
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<WarehouseEntity>> violations = validator.validate(warehouse);
        if (!violations.isEmpty()) {
            System.out.println("VALIDATIONS IS NOT EMPTY");
            //TODO: Log error for validation
            throw new BLException(1L, violations.stream().map(Objects::toString).collect(Collectors.joining("\n")), null);
        }

        //Iterate through the list of WarehouseNextHops and store the elements.
        for (WarehouseNextHopsEntity nextHop: warehouse.getNextHops()) {
            HopEntity savedHop = saveHop(nextHop.getHop());
            nextHop.getHop().setId(savedHop.getId());
            nextHop.setWarehouse(warehouse);
        }

        //Store coordinates from warehouse
        GeoCoordinateEntity savedCoordinates = geoCoordinateRepository.save(warehouse.getLocationCoordinates());
        warehouse.getLocationCoordinates().setId(savedCoordinates.getId());

        WarehouseEntity savedWarehouse = warehouseRepository.save(warehouse);
        return  savedWarehouse;
    }

    private TruckEntity saveTruck(TruckEntity truck) {
        GeoCoordinateEntity savedGeoCoordinates = geoCoordinateRepository.save(truck.getLocationCoordinates());
        truck.getLocationCoordinates().setId(savedGeoCoordinates.getId());
        TruckEntity savedTruck = truckRepository.save(truck);
        return savedTruck;
    }

    public HopEntity saveHop(HopEntity hop) throws BLException {
        System.out.println("Saving Hop Entity of type " + hop.getHopType());

        if (hop.getHopType().toLowerCase().equals("truck")) {
            System.out.println("Casting into TruckEntity");
            TruckEntity truck = (TruckEntity) hop;
            System.out.println("Truck code is " + truck.getCode());
            TruckEntity savedTruck = saveTruck(truck);
            System.out.println("Truck successfully saved");
            return savedTruck;
        }
        else if (hop.getHopType().toLowerCase().equals("warehouse")) {
            System.out.println("Casting into WarehouseEntity");
            //WarehouseEntity warehouse = hop.toWarehouse();
            WarehouseEntity warehouse = (WarehouseEntity) hop;
            System.out.println("Warehouse code is " + warehouse.getCode());
            WarehouseEntity savedWarehouse = saveWarehouse(warehouse);
            System.out.println("Warehouse successfully saved");

            return savedWarehouse;
        }
        else if (hop.getHopType().toLowerCase().equals("transferwarehouse")) {
            //TODO: Store transferwarehouse
        }
        else {
            //TODO: Store hop with no HopType (no child Hop)
        }
        return null;
    }

    @Override
    public void importWarehouses(WarehouseEntity warehouse) throws BLException {

        try{
            resetDB();
        }
        catch (Exception e) {
            log.error("Failed to reset Database: " + e.getMessage());
            throw new BLException(3L, "Failed to reset Database: ",  e);
        }


        try{
            WarehouseEntity savedWarehouse = saveWarehouse(warehouse);

        }
        catch (Exception e) {
            log.error("Failed to import Warehouses: " + e.getMessage());
            throw new BLException(2L, "Failed to import Warehouses: ",  e);
        }

    }
}