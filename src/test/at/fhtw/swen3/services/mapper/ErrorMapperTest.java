package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ErrorMapperTest {
    ErrorEntity errorEntity;
    Error errorDto;

    @BeforeEach
    void setUp(){
        errorDto = new Error();
        errorDto.setErrorMessage("Das ist einen Fehler");
        errorEntity = new ErrorEntity();

    }

    @Test
    void dtoToEntityTest(){
        ErrorEntity errorEntityTest = ErrorMapper.INSTANCE.dtoToEntity(errorDto);
        assertEquals(errorDto.getErrorMessage(), errorEntityTest.getErrorMessage());
    }
    @Test
    void entityToDtoTest(){
        Error errorTest = ErrorMapper.INSTANCE.entityToDto(errorEntity);
        assertEquals(errorEntity.getErrorMessage(), errorTest.getErrorMessage());

    }
}
