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
            System.out.println("Truck " + truck.getCode() + " could not be stored: ");
            System.out.println("Number Plate: " + truck.getNumberPlate());
            System.out.println("RegionGeoJson: " + truck.getRegionGeoJson());
            System.out.println("Hop Type: " + truck.getHopType());
            System.out.println("ProcessingDelay: " + truck.getProcessingDelayMins());
            System.out.println("Description: " + truck.getDescription());
            System.out.println("Location Name: " + truck.getLocationName());
            System.out.println("Coordinates: " + truck.getLocationCoordinates().getLat() + " " + truck.getLocationCoordinates().getLon());
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
            System.out.println("LocationCoordinates from truck  " + transferwarehouse.getCode() + " could not be stored: ");
        }

        transferwarehouse.getLocationCoordinates().setId(savedGeoCoordinates.getId());
        TransferwarehouseEntity savedTransferwarehouse = null;

        try{

            transferwarehouse.setRegionGeo(geoEncodingService.getRegion(transferwarehouse.getRegionGeoJson()));
            savedTransferwarehouse = transferwarehouseRepository.save(transferwarehouse);
            System.out.println("Transferwarehouse "+ savedTransferwarehouse.getCode() + " stored correctly");
        }
        catch (Exception e) {
            log.error("Failed to store transferwarehouse: " + e.getMessage());
            System.out.println("Transferwarehouse " + transferwarehouse.getCode() + " could not be stored: ");
            System.out.println("Logistic Partner: " + transferwarehouse.getLogisticsPartner());
            System.out.println("RegionGeoJson: " + transferwarehouse.getRegionGeoJson());
            System.out.println("Hop Type: " + transferwarehouse.getHopType());
            System.out.println("ProcessingDelay: " + transferwarehouse.getProcessingDelayMins());
            System.out.println("Description: " + transferwarehouse.getDescription());
            System.out.println("Location Name: " + transferwarehouse.getLocationName());
            System.out.println("Coordinates: " + transferwarehouse.getLocationCoordinates().getLat() + " " + transferwarehouse.getLocationCoordinates().getLon());
            throw new BLException(3L, "Failed to store Truck in Database: ",  e);

        }
        return savedTransferwarehouse;
    }

    public HopEntity saveHop(HopEntity hop) throws BLException {
        System.out.println("Saving Hop Entity of type " + hop.getHopType());

        // set coordinates as Points --> this could be done in Mapping. s
        hop.getLocationCoordinates().setCoordinates();

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
            System.out.println("Casting into Transferwarehouse");
            TransferwarehouseEntity transferwarehouse = (TransferwarehouseEntity) hop;
            System.out.println("Transferwarehouse code is " + transferwarehouse.getCode());
            TransferwarehouseEntity savedTransferwarehouse = saveTransferwarehouse(transferwarehouse);
            System.out.println("Transferwarehouse successfully saved");
            return savedTransferwarehouse;
        }
        else {
            //TODO: Store hop with no HopType (no child Hop)
            System.out.println("Hop has no HopType");
            System.out.println("Hop " + hop.getCode() + " has type " + hop.getHopType());
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
            System.out.println("Warehouse "+ savedWarehouse.getCode() + " stored correctly");
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
        HopEntity hopEntity = null;
        try {
            hopEntity = hopRepository.findDistinctFirstByCode(code);

        } catch (Exception e) {
            throw new BLException(5L, "HopEntity could not be exported: ", e);
        }

        return hopEntity;
    }
}