package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipientMapper {
    RecipientMapper INSTANCE = Mappers.getMapper(RecipientMapper.class);

    @Mapping(source= "recipientDto.country", target="country")
    @Mapping(source= "recipientDto.city", target="city")
    @Mapping(source= "recipientDto.postalCode", target="postalCode")
    @Mapping(source= "recipientDto.street", target="street")
    @Mapping(source= "recipientDto.name", target="name")
    RecipientEntity dtoToEntity(Recipient recipientDto);

    @Mapping(source= "recipientEntity.country", target="country")
    @Mapping(source= "recipientEntity.city", target="city")
    @Mapping(source= "recipientEntity.postalCode", target="postalCode")
    @Mapping(source= "recipientEntity.street", target="street")
    @Mapping(source= "recipientEntity.name", target="name")
    Recipient entityToDto(RecipientEntity recipientEntity);
}
