package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
@SpringBootTest
public class WarehouseValidationTest {

    @Test
    public void validationTest_warehouse() {

        log.info("TEST validationTest_warehouse");

        final GeoCoordinateEntity geoCoordinateEntity = GeoCoordinateEntity.builder()
                .id(12L)
                .lat(12.3)
                .lon(34.4)
                .build();

        final HopEntity hopEntity = new HopEntity();
        hopEntity.setId(12L);
        hopEntity.setCode("ABCD12");
        hopEntity.setHopType("type");
        hopEntity.setDescription("Warehouse 27-12");
        hopEntity.setLocationName("Damascus");
        hopEntity.setProcessingDelayMins(12);
        hopEntity.setLocationCoordinates(geoCoordinateEntity);

        final WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsEntity.builder()
                .id(25L)
                .warehouse(hopEntity)
                .traveltimeMins(23)
                .hop(hopEntity)
                .build();

        List<WarehouseNextHopsEntity> hops = new ArrayList<>();
        hops.add(warehouseNextHopsEntity);

        final WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(12L)
                .code("ABCD12")
                .hopType("R")
                .processingDelayMins(25)
                .locationName("Aleppo")
                .description("This is a description for WarehouseEntity in validationTest-warehouse")
                .locationCoordinates(geoCoordinateEntity)
                .level(25)
                .nextHops(hops)
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<WarehouseEntity>> violations = validator.validate(warehouseEntity);
        for (ConstraintViolation<WarehouseEntity> violation : violations)
        {
            log.error(violation.getMessage());
            fail(violation.getMessage());
        }
    }

    @Test
    public void validationTest_warehouse_shouldFail() {
        log.info("TEST validationTest_warehouse_shouldFail");
        final GeoCoordinateEntity geoCoordinateEntity = GeoCoordinateEntity.builder()
                .id(12L)
                .lat(12.3)
                .lon(34.4)
                .build();

        final HopEntity hopEntity = new HopEntity();
        hopEntity.setId(12L);
        hopEntity.setCode("ABCD12");
        hopEntity.setHopType("type");
        hopEntity.setDescription("Warehouse 27-12");
        hopEntity.setLocationName("Damascus");
        hopEntity.setProcessingDelayMins(12);
        hopEntity.setLocationCoordinates(geoCoordinateEntity);

        final WarehouseNextHopsEntity warehouseNextHopsEntity = WarehouseNextHopsEntity.builder()
                .id(25L)
                .warehouse(hopEntity)
                .traveltimeMins(23)
                .hop(hopEntity)
                .build();

        List<WarehouseNextHopsEntity> hops = new ArrayList<>();
        hops.add(warehouseNextHopsEntity);

        final WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(12L)
                .code("*****")
                .hopType("R")
                .processingDelayMins(25)
                .locationName("Aleppo")
                .description("This is a description for WarehouseEntity in validationTest_warehouse_shouldFail")
                .locationCoordinates(geoCoordinateEntity)
                .level(25)
                .nextHops(hops)
                .build();


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<WarehouseEntity>> violations = validator.validate(warehouseEntity);
        for (ConstraintViolation<WarehouseEntity> violation : violations)
        {
            log.error(violation.getMessage());
            return;
        }
        fail("Validation should fail!");

    }

}
