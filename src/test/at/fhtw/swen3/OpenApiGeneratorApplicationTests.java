package at.fhtw.swen3;

import at.fhtw.swen3.persistence.NewParcelInfo;
import at.fhtw.swen3.persistence.Parcel;
import at.fhtw.swen3.persistence.Recipient;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.ParcelDTO;
import at.fhtw.swen3.services.mapper.IParcelMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = {OpenApiGeneratorApplication.class})
class OpenApiGeneratorApplicationTests {

    @Test
    void contextLoads() {
    }

    //ParcelDTO entityToDto(ParcelEntity parcelEntity);

    //ParcelEntity dtoToEntity(Parcel parcel);


}