package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.Parcel;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.ParcelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "parcel")
public interface IParcelMapper {

    IParcelMapper INSTANCE = Mappers.getMapper(IParcelMapper.class);



    @Mapping(source = "parcelEntity.parcel.weight", target = "weight")
    @Mapping(source = "parcelEntity.parcel.sender", target = "sender")
    @Mapping(source = "parcelEntity.parcel.recipient", target = "recipient")
    ParcelDTO parcelEntityToParcelDto(ParcelEntity parcelEntity);

    @Mapping(source="parceldto.weight", target="parcel.weight")
    @Mapping(source="parceldto.recipient", target="parcel.recipient")
    @Mapping(source="parceldto.sender", target="parcel.sender")
    ParcelEntity parcelDtoToParcelEntity(ParcelDTO parceldto);

    //@Mapping(source= "parcel", target="parcel")
    //ParcelEntity parcelToEntity(Parcel parcel);

}

