package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransferwarehouseMapperTest {

    @Test
    void dtoToEntity(){
        Transferwarehouse transferwarehouseDto = Transferwarehouse.builder()

                .build();

        //RecipientEntity recipientEntityTest = TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouseDto);
        //assertEquals(recipientDto.getName(), recipientEntityTest.getName());


    }
    @Test
    void entityToDto(){

        //Recipient recipientTest = RecipientMapper.INSTANCE.entityToDto(recipientEntity);


    }


}
