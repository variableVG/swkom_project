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

    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "sender", target = "sender")
    @Mapping(source = "recipient", target = "recipient")
    Parcel parcelEntityToParcelDto(ParcelEntity parcelEntity);
    @Mapping(source = "trackingId", target = "trackingId")
    NewParcelInfo parcelEntityToNewParcelInfoDto(ParcelEntity parcelEntity);
    @Mapping(source="trackingId", target="trackingId")
    ParcelEntity newParcelInfoDtoToParcelEntity(NewParcelInfo newParcelInfo);

    @Mapping(source="weight", target="weight")
    @Mapping(source="recipient", target="recipient")
    @Mapping(source="sender", target="sender")
    ParcelEntity parcelDtoToParcelEntity(Parcel parcel);

    /*@Mapping(source="weight", target="weight")
    @Mapping(source="recipient", target="recipient")
    @Mapping(source="sender", target="sender")
    @Mapping(source="trackingId", target="trackingId")
    @Mapping(source="state", target="state")
    @Mapping(source="visitedHops", target="visitedHops")
    @Mapping(source="futureHops", target="futureHops")
    ParcelEntity dtoToToParcelEntity(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation);*/

    @Mapping(source= "state", target="state")
    @Mapping(source= "visitedHops", target="visitedHops")
    @Mapping(source= "futureHops", target="futureHops")
    TrackingInformation parcelEntityToTrackingInformationDto(ParcelEntity parcelEntity);




}

