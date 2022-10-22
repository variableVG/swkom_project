package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "parcel")
public interface IParcelMapper {
    IParcelMapper INSTANCE = Mappers.getMapper(IParcelMapper.class);

    @Mapping(source = "parcelEntity.parcelModelEntity.weight", target = "weight")
    @Mapping(source = "parcelEntity.parcelModelEntity.sender", target = "sender")
    @Mapping(source = "parcelEntity.parcelModelEntity.recipient", target = "recipient")
    Parcel parcelEntityToParcelDto(ParcelEntity parcelEntity);
    @Mapping(source = "parcelEntity.newParcelInfoEntity.trackingId", target = "trackingId")
    NewParcelInfo parcelEntityToNewParcelInfoDto(ParcelEntity parcelEntity);

    @Mapping(source="parcel.weight", target="parcelModelEntity.weight")
    @Mapping(source="parcel.recipient", target="parcelModelEntity.recipient")
    @Mapping(source="parcel.sender", target="parcelModelEntity.sender")
    ParcelEntity parcelDtoToParcelEntity(Parcel parcel);

    @Mapping(source="parcel.weight", target="parcelModelEntity.weight")
    @Mapping(source="parcel.recipient", target="parcelModelEntity.recipient")
    @Mapping(source="parcel.sender", target="parcelModelEntity.sender")
    @Mapping(source="newParcelInfo.trackingId", target="newParcelInfoEntity.trackingId")
    @Mapping(source="trackingInformation.state", target="trackingInformationEntity.state")
    @Mapping(source="trackingInformation.visitedHops", target="trackingInformationEntity.visitedHops")
    @Mapping(source="trackingInformation.futureHops", target="trackingInformationEntity.futureHops")
    ParcelEntity dtoToToParcelEntity(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation);



}

