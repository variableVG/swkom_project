package at.fhtw.swen3.services.mapper;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RecipientMapperTest {
    Recipient recipientDto;
    RecipientEntity recipientEntity;

    @BeforeEach
    void setUp(){
        recipientDto = Recipient.builder()
                .name("Rawan")
                .street("Straße A")
                .postalCode("1010")
                .city("Vienna")
                .country("Austria").build();

        recipientEntity = RecipientEntity.builder()
                .name("Ali")
                .street("spenger")
                .postalCode("1050")
                .city("Damas")
                .country("syria").build();

    }
    @Test
    void dtoToEntityTest(){
        RecipientEntity recipientEntityTest = RecipientMapper.INSTANCE.dtoToEntity(recipientDto);
        assertEquals(recipientDto.getName(), recipientEntityTest.getName());
        assertEquals(recipientDto.getCity(), recipientEntityTest.getCity());
        assertEquals(recipientDto.getCountry(), recipientEntityTest.getCountry());
        assertEquals(recipientDto.getStreet(), recipientEntityTest.getStreet());
        assertEquals(recipientDto.getPostalCode(), recipientEntityTest.getPostalCode());

    }
    @Test
    void entityToDtoTest(){

        Recipient recipientTest = RecipientMapper.INSTANCE.entityToDto(recipientEntity);
        assertEquals(recipientEntity.getName(), recipientTest.getName());
        assertEquals(recipientEntity.getCity(), recipientTest.getCity());
        assertEquals(recipientEntity.getCountry(), recipientTest.getCountry());
        assertEquals(recipientEntity.getStreet(), recipientTest.getStreet());
        assertEquals(recipientEntity.getPostalCode(), recipientTest.getPostalCode());

    }


}
