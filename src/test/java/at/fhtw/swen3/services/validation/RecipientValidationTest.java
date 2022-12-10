package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;
@Slf4j
@SpringBootTest
public class RecipientValidationTest {
    // private static final Logger log = LoggerFactory.getLogger(RecipientValidationTest.class);
    @Test
    public void validationTest_recipient() {
        log.info("TEST validationTest_recipient");
        final RecipientEntity recipient = RecipientEntity.builder()
                .name("Rawan")
                .street("Hauptstraße 12/12/12")
                .postalCode("A-1020")
                .city("vienna")
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
        log.info("TEST validationTest_recipient_shouldFail");
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
