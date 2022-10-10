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
    @Mapping(source = "parcelEntity.newParcelInfo.trackingId", target = "trackingId")
    ParcelDTO entityToDto(ParcelEntity parcelEntity);

    ParcelEntity dtoToEntity(Parcel parcel);

    //@Mapping(source= "parcel", target="parcel")
    //ParcelEntity parcelToEntity(Parcel parcel);

}

