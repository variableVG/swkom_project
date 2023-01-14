package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
    private TransferwarehouseRepository transferwarehouseRepository;
    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    @Autowired
    private MyValidator myValidator;
    public GeoEncodingService geoEncodingService;

    private void resetDB() {

        warehouseNextHopsRepository.deleteAll();
        warehouseRepository.deleteAll();
        hopRepository.deleteAll();
        truckRepository.deleteAll();
        transferwarehouseRepository.deleteAll();
        geoCoordinateRepository.deleteAll();
    }


    private WarehouseEntity saveWarehouse(WarehouseEntity warehouse) throws BLException {
        //Validation:

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<WarehouseEntity>> violations = validator.validate(warehouse);
        if (!violations.isEmpty()) {
            log.error("VALIDATIONS IS NOT EMPTY");
            throw new BLException(1L, violations.stream().map(Objects::toString).collect(Collectors.joining("\n")), null);
        }

        //Iterate through the list of WarehouseNextHops and store the elements.
        for (WarehouseNextHopsEntity nextHop: warehouse.getNextHops()) {
            HopEntity savedHop = saveHop(nextHop.getHop());
            nextHop.getHop().setId(savedHop.getId());
            nextHop.setWarehouse(warehouse);
        }

        //Store coordinates from warehouse
        warehouse.getLocationCoordinates().setCoordinates();
        GeoCoordinateEntity savedCoordinates = geoCoordinateRepository.save(warehouse.getLocationCoordinates());
        warehouse.getLocationCoordinates().setId(savedCoordinates.getId());

        WarehouseEntity savedWarehouse = warehouseRepository.save(warehouse);
        return  savedWarehouse;
    }

    private TruckEntity saveTruck(TruckEntity truck) throws BLException {
        GeoCoordinateEntity savedGeoCoordinates = null;

        try{
            savedGeoCoordinates = geoCoordinateRepository.save(truck.getLocationCoordinates());
        }
        catch (Exception e) {
            log.error("Failed to store LocationCoordinates from truck " + truck.getCode() + ": " + e.getMessage());
            System.out.println("LocationCoordinates from truck  " + truck.getCode() + " could not be stored: ");
        }

        truck.getLocationCoordinates().setId(savedGeoCoordinates.getId());
        TruckEntity savedTruck = null;

        try{

            truck.setRegionGeo(geoEncodingService.getRegion(truck.getRegionGeoJson()));
            savedTruck = truckRepository.save(truck);
            System.out.println("Truck "+ savedTruck.getCode() + " stored correctly");
        }
        catch (Exception e) {
            log.error("Failed to store Truck: " + e.getMessage());
            log.info("Truck " + truck.getCode() + " could not be stored: ");
            log.info("Number Plate: " + truck.getNumberPlate());
            log.info("RegionGeoJson: " + truck.getRegionGeoJson());
            log.info("Hop Type: " + truck.getHopType());
            log.info("ProcessingDelay: " + truck.getProcessingDelayMins());
            log.info("Description: " + truck.getDescription());
            log.info("Location Name: " + truck.getLocationName());
            log.info("Coordinates: " + truck.getLocationCoordinates().getLat() + " " + truck.getLocationCoordinates().getLon());
            throw new BLException(3L, "Failed to store Truck in Database: ",  e);

        }
        return savedTruck;
    }

    private TransferwarehouseEntity saveTransferwarehouse(TransferwarehouseEntity transferwarehouse) throws BLException {
        GeoCoordinateEntity savedGeoCoordinates = null;

        try{
            savedGeoCoordinates = geoCoordinateRepository.save(transferwarehouse.getLocationCoordinates());
        }
        catch (Exception e) {
            log.error("Failed to store LocationCoordinates from truck " + transferwarehouse.getCode() + ": " + e.getMessage());
        }

        transferwarehouse.getLocationCoordinates().setId(savedGeoCoordinates.getId());
        TransferwarehouseEntity savedTransferwarehouse = null;

        try{

            transferwarehouse.setRegionGeo(geoEncodingService.getRegion(transferwarehouse.getRegionGeoJson()));
            savedTransferwarehouse = transferwarehouseRepository.save(transferwarehouse);
            log.info("Transferwarehouse "+ savedTransferwarehouse.getCode() + " stored correctly");
        }
        catch (Exception e) {
            log.error("Failed to store transferwarehouse: " + e.getMessage());
            log.error("Transferwarehouse " + transferwarehouse.getCode() + " could not be stored: ");
            log.info("Logistic Partner: " + transferwarehouse.getLogisticsPartner());
            log.info("RegionGeoJson: " + transferwarehouse.getRegionGeoJson());
            log.info("Hop Type: " + transferwarehouse.getHopType());
            log.info("ProcessingDelay: " + transferwarehouse.getProcessingDelayMins());
            log.info("Description: " + transferwarehouse.getDescription());
            log.info("Location Name: " + transferwarehouse.getLocationName());
            log.info("Coordinates: " + transferwarehouse.getLocationCoordinates().getLat() + " " + transferwarehouse.getLocationCoordinates().getLon());
            throw new BLException(3L, "Failed to store Truck in Database: ",  e);

        }
        return savedTransferwarehouse;
    }

    public HopEntity saveHop(HopEntity hop) throws BLException {
        log.info("Saving Hop Entity of type " + hop.getHopType());

        // set coordinates as Points --> this could be done in Mapping. s
        hop.getLocationCoordinates().setCoordinates();

        switch (hop.getHopType().toLowerCase()) {
            case "truck" -> {
                log.info("Casting into TruckEntity");
                TruckEntity truck = (TruckEntity) hop;
                log.info("Truck code is " + truck.getCode());
                TruckEntity savedTruck = saveTruck(truck);
                log.info("Truck successfully saved");
                return savedTruck;
            }
            case "warehouse" -> {
                log.info("Casting into WarehouseEntity");
                //WarehouseEntity warehouse = hop.toWarehouse();
                WarehouseEntity warehouse = (WarehouseEntity) hop;
                log.info("Warehouse code is " + warehouse.getCode());
                WarehouseEntity savedWarehouse = saveWarehouse(warehouse);
                log.info("Warehouse successfully saved");
                return savedWarehouse;
            }
            case "transferwarehouse" -> {
                log.info("Casting into Transferwarehouse");
                TransferwarehouseEntity transferwarehouse = (TransferwarehouseEntity) hop;
                log.info("Transferwarehouse code is " + transferwarehouse.getCode());
                TransferwarehouseEntity savedTransferwarehouse = saveTransferwarehouse(transferwarehouse);
                log.info("Transferwarehouse successfully saved");
                return savedTransferwarehouse;
            }
            default -> {
                //TODO: Store hop with no HopType (no child Hop)
                log.info("Hop has no HopType");
                log.info("Hop " + hop.getCode() + " has type " + hop.getHopType());
            }
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
            log.info("Warehouse "+ savedWarehouse.getCode() + " stored correctly");
        }
        catch (Exception e) {
            log.error("Failed to import Warehouses: " + e.getMessage());
            throw new BLException(2L, "Failed to import Warehouse" + warehouse.getCode() + " : ",  e);
        }

    }

    @Override
    public WarehouseEntity exportWarehouses() throws BLException {
        WarehouseEntity warehouseEntity = null;
        try {
            warehouseEntity = warehouseRepository.getRoot();

        } catch (Exception e) {
            throw new BLException(5L, "Warehouse could not be exported: ", e);
        }

        return warehouseEntity;

    }

    @Override
    public HopEntity getWarehouse(String code) throws BLException {

        myValidator.validate(code);
        HopEntity hopEntity = null;
        try {
            hopEntity = hopRepository.findDistinctFirstByCode(code);

        } catch (Exception e) {
            throw new BLException(5L, "HopEntity could not be exported: ", e);
        }

        return hopEntity;
    }
}