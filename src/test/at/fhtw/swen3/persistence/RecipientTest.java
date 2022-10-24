package at.fhtw.swen3.persistence;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

//@SpringBootTest
public class RecipientTest {
    private static final Logger log = LoggerFactory.getLogger(RecipientTest.class);
    @Test
    public void validationTest_recipient() {
        log.info("TEST validationTest");
        final RecipientEntity recipient = RecipientEntity.builder()
                .name("Rawan")
                .street("Spenger")
                .postalCode("A-1120")
                .city("Wien")
                .country("Austria")
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(recipient);
        for (ConstraintViolation<RecipientEntity> violation : violations)
        {
            log.error(violation.getMessage());
            fail(violation.getMessage());
        }
    }
    @Test
    public void validationTest_recipient_shouldFail() {
        log.info("TEST validationTest");
        final RecipientEntity recipient = RecipientEntity.builder()
                .name("Rawan123")
                .street("Spenger")
                .postalCode("A-113")
                .city("Wien4")
                .country("Austria")
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RecipientEntity>> violations = validator.validate(recipient);
        for (ConstraintViolation<RecipientEntity> violation : violations)
        {
            log.error(violation.getMessage());
            return;
        }
        fail("Validation should fail!");

    }

}
