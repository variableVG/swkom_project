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
public class WarehouseValidationTest {
    private static final Logger log = LoggerFactory.getLogger(WarehouseValidationTest.class);
/*
    @Test
    public void validationTest_shouldBeOk() {
        log.info("TEST validationTest");
        final Author rudi = new Author("Rudi", "Ratlos", LocalDate.of(1976, 06, 03));
        final Book lord_of_the_rings = new Book("Lord of the rings", rudi, 1000, "With the ring on the way", 99.0f);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Book>> violations = validator.validate(lord_of_the_rings);
        for (ConstraintViolation<Book> violation : violations)
        {
            log.error(violation.getMessage());
            fail(violation.getMessage());
        }

    }
*/
}
