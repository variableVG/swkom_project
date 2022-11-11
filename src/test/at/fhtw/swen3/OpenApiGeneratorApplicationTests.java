package at.fhtw.swen3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest(classes = {OpenApiGeneratorApplication.class})
class OpenApiGeneratorApplicationTests {

    @Test
    void contextLoads() {
    }

    //ParcelDTO entityToDto(ParcelEntity parcelEntity);

    //ParcelEntity dtoToEntity(Parcel parcel);


}