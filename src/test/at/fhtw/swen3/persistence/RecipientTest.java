package at.fhtw.swen3.persistence;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class RecipientTest {
    private static final Logger log = LoggerFactory.getLogger(RecipientTest.class);
    @Test
    public void validationTest_recipient() {
        log.info("TEST validationTest");
        final Recipient recipient = new Recipient("Rawan", "spenger", "1130", "Wien", "Austria");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Recipient>> violations = validator.validate(recipient);
        for (ConstraintViolation<Recipient> violation : violations)
        {
            log.error(violation.getMessage());
            fail(violation.getMessage());
        }
    }
    @Test
    public void validationTest_recipient_shouldFail() {
        log.info("TEST validationTest");
        final Recipient recipient = new Recipient("Rawan", "spenger", "113", "Wien", "Austria");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Recipient>> violations = validator.validate(recipient);
        for (ConstraintViolation<Recipient> violation : violations)
        {
            log.error(violation.getMessage());
            return;
        }
        fail("Validation should fail!");

    }

}
