package at.fhtw.swen3.persistence;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import at.fhtw.swen3.services.mapper.HopArrivalMapperTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ParcelValidationTest {
    private static final Logger log = LoggerFactory.getLogger(ParcelValidationTest.class);

    @Test
    public void validationTest_parcel() {
        log.info("TEST validationTest");
        final ParcelEntity parcel = new ParcelEntity();
        final RecipientEntity recipientEntity = RecipientEntity.builder()
                .name("Rawan")
                .street("Spenger")
                .postalCode("A-1120")
                .city("vienna")
                .country("Austria")
                .build();
        final RecipientEntity recipientEntitySender = RecipientEntity.builder()
                .name("Rawan")
                .city("Damaskus")
                .country("Syrien")
                .street("masaken")
                .postalCode("A-1120").build();
        final RecipientEntity recipientEntityRecipient = RecipientEntity.builder()
                .name("Ali")
                .city("Damaskus")
                .country("Syrien")
                .street("masaken")
                .postalCode("A-1120").build();
        parcel.setWeight(Float.valueOf(12));
        parcel.setTrackingId("34");
        parcel.setRecipient(recipientEntity);
        parcel.setState(ParcelEntity.StateEnum.DELIVERED );
        parcel.setSender(recipientEntitySender);
        parcel.setRecipient(recipientEntityRecipient);
        List<HopArrivalEntity> nextHops = new ArrayList<>();
        final HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder()
                .code("ABCD12")
                .description("Hallo")
                .dateTime(OffsetDateTime.MIN).build();
        nextHops.add(hopArrivalEntity);
        parcel.setFutureHops(nextHops);
        parcel.setVisitedHops(nextHops);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(parcel);
        for (ConstraintViolation<ParcelEntity> violation : violations)
        {
            log.error(violation.getMessage());
            fail(violation.getMessage());
        }
    }
    @Test
    public void validationTest_parcel_shouldFail() {
        log.info("TEST validationTest");
        final ParcelEntity parcel = new ParcelEntity();
        final RecipientEntity recipientEntity = RecipientEntity.builder()
                .name("Rawan")
                .street("Spenger")
                .postalCode("A-1120")
                .city("vienna")
                .country("Austria")
                .build();
        final RecipientEntity recipientEntitySender = RecipientEntity.builder()
                .name("Rawan")
                .city("Damaskus")
                .country("Syrien")
                .street("masaken")
                .postalCode("A-1120").build();
        final RecipientEntity recipientEntityRecipient = RecipientEntity.builder()
                .name("Ali")
                .city("Damaskus")
                .country("Syrien")
                .street("masaken")
                .postalCode("A-1120").build();
        parcel.setWeight(Float.valueOf(-1));
        parcel.setTrackingId("34");
        parcel.setRecipient(recipientEntity);
        parcel.setState(ParcelEntity.StateEnum.DELIVERED );
        parcel.setSender(recipientEntitySender);
        parcel.setRecipient(recipientEntityRecipient);
        List<HopArrivalEntity> nextHops = new ArrayList<>();
        final HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder()
                .code("ABCD12")
                .description("Hallo")
                .dateTime(OffsetDateTime.MIN).build();
        nextHops.add(hopArrivalEntity);
        parcel.setFutureHops(nextHops);
        parcel.setVisitedHops(nextHops);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(parcel);
        for (ConstraintViolation<ParcelEntity> violation : violations)
        {
            log.error(violation.getMessage());
            return;
        }
        fail("Validation should fail!");

    }
}