package at.fhtw.swen3.persistence;

import at.fhtw.swen3.persistence.entity.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class HopValidationTest {
    private static final Logger log = LoggerFactory.getLogger(HopValidationTest.class);
    @Test
    public void validationTest_warehouse() {
        log.info("TEST validationTest");
        final GeoCoordinateEntity geoCoordinateEntity = GeoCoordinateEntity.builder()
                .id(Long.valueOf(12))
                .lat(12.3)
                .lon(34.4)
                .build();
        final HopEntity hopEntity = new HopEntity();
        hopEntity.setId(Long.valueOf(12));
        hopEntity.setCode("ABCD12");
        hopEntity.setHopType("type");
        hopEntity.setDescription("Warehouse 27-12");
        hopEntity.setLocationName("Damas");
        hopEntity.setProcessingDelayMins(12);
        hopEntity.setLocationCoordinates(geoCoordinateEntity);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<HopEntity>> violations = validator.validate(hopEntity);
        for (ConstraintViolation<HopEntity> violation : violations)
        {
            log.error(violation.getMessage());
            fail(violation.getMessage());
        }

    }
    @Test
    public void validationTest_warehouse_shouldFail() {
        log.info("TEST validationTest");
        final GeoCoordinateEntity geoCoordinateEntity = GeoCoordinateEntity.builder()
                .id(Long.valueOf(12))
                .lat(12.3)
                .lon(34.4)
                .build();
        final HopEntity hopEntity = new HopEntity();
        hopEntity.setId(Long.valueOf(12));
        hopEntity.setCode("ABCD12");
        hopEntity.setHopType("type");
        hopEntity.setDescription("******");
        hopEntity.setLocationName("Damas");
        hopEntity.setProcessingDelayMins(12);
        hopEntity.setLocationCoordinates(geoCoordinateEntity);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<HopEntity>> violations = validator.validate(hopEntity);
        for (ConstraintViolation<HopEntity> violation : violations)
        {
            log.error(violation.getMessage());
            return;
        }
        fail("Validation should fail!");

    }

}