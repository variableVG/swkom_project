package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "parcel")
public interface ParcelMapper {
    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(source = "parcelEntity.weight", target = "weight")
    @Mapping(source = "parcelEntity.sender", target = "sender")
    @Mapping(source = "parcelEntity.recipient", target = "recipient")
    Parcel parcelEntityToParcelDto(ParcelEntity parcelEntity);
    @Mapping(source = "parcelEntity.trackingId", target = "trackingId")
    NewParcelInfo parcelEntityToNewParcelInfoDto(ParcelEntity parcelEntity);
    @Mapping(source="newParcelInfo.trackingId", target="trackingId")
    ParcelEntity newParcelInfoDtoToParcelEntity(NewParcelInfo newParcelInfo);

    @Mapping(source="parcel.weight", target="weight")
    @Mapping(source="parcel.recipient", target="recipient")
    @Mapping(source="parcel.sender", target="sender")
    ParcelEntity parcelDtoToParcelEntity(Parcel parcel);

    @Mapping(source="parcel.weight", target="weight")
    @Mapping(source="parcel.recipient", target="recipient")
    @Mapping(source="parcel.sender", target="sender")
    @Mapping(source="newParcelInfo.trackingId", target="trackingId")
    @Mapping(source="trackingInformation.state", target="state")
    @Mapping(source="trackingInformation.visitedHops", target="visitedHops")
    @Mapping(source="trackingInformation.futureHops", target="futureHops")
    ParcelEntity dtoToToParcelEntity(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation);

    @Mapping(source= "parcelEntity.state", target="state")
    @Mapping(source= "parcelEntity.visitedHops", target="visitedHops")
    @Mapping(source= "parcelEntity.futureHops", target="futureHops")
    TrackingInformation parcelEntityToTrackingInformationDto(ParcelEntity parcelEntity);




}

