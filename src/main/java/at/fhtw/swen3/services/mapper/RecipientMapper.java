package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipientMapper {
    RecipientMapper INSTANCE = Mappers.getMapper(RecipientMapper.class);

    @Mapping(source= "country", target="country")
    @Mapping(source= "city", target="city")
    @Mapping(source= "postalCode", target="postalCode")
    @Mapping(source= "street", target="street")
    @Mapping(source= "name", target="name")
    RecipientEntity dtoToEntity(Recipient recipientDto);

    @Mapping(source= "country", target="country")
    @Mapping(source= "city", target="city")
    @Mapping(source= "postalCode", target="postalCode")
    @Mapping(source= "street", target="street")
    @Mapping(source= "name", target="name")
    Recipient entityToDto(RecipientEntity recipientEntity);
}
