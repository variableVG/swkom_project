package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;

public class BusinessLayer {

    public TrackingInformation trackParcel(String trackingId) {
        NewParcelInfo newParcelInfoDto = new NewParcelInfo(trackingId);
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.newParcelInfoDtoToParcelEntity(newParcelInfoDto);
        //TODO: change with dataset!
        parcelEntity.trackParcel(trackingId);
        return ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);
    }

    public NewParcelInfo submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);
        //TODO: change with dataset!
        parcelEntity.submitParcel();
        NewParcelInfo newParcelInfoDto = ParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(parcelEntity);
        return newParcelInfoDto;
    }
}
