package at.fhtw.swen3.configuration;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.BingEncodingProxy;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.validation.MyValidator;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//this class registers the Service classes and instantiates them through the @Bean Annotation,
// replacing our field Injection approach and removing the @Service and @Autowire Annotation
@Configuration
public class AppConfig {

    @Bean
    public ParcelServiceImpl parcelService(ParcelRepository parcelRepository, RecipientRepository recipientRepository, HopRepository hopRepository , TruckRepository truckRepository, TransferwarehouseRepository transferwarehouseRepository, HopArrivalRepository hopArrivalRepository,MyValidator validator, GeoEncodingService geoEncodingService) {
        return new ParcelServiceImpl(parcelRepository, recipientRepository, hopRepository, truckRepository, transferwarehouseRepository, hopArrivalRepository ,validator, geoEncodingService);
    }

    @Bean
    public WarehouseServiceImpl warehouseService(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, HopRepository hopRepository, TruckRepository truckRepository, TransferwarehouseRepository transferwarehouseRepository, GeoCoordinateRepository geoCoordinateRepository, MyValidator validator, GeoEncodingService geoEncodingService) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, hopRepository, truckRepository, transferwarehouseRepository, geoCoordinateRepository, validator, geoEncodingService);
    }
}
