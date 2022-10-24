package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.IParcelMapper;

public class BusinessLayer {

    public TrackingInformation trackParcel(String trackingId) {
        NewParcelInfo newParcelInfoDto = new NewParcelInfo(trackingId);
        ParcelEntity parcelEntity = IParcelMapper.INSTANCE.newParcelInfoDtoToParcelEntity(newParcelInfoDto);
        //TODO: change with dataset!
        parcelEntity.trackParcel(trackingId);
        return IParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);
    }

    public NewParcelInfo submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = IParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);
        //TODO: change with dataset!
        parcelEntity.submitParcel();
        NewParcelInfo newParcelInfoDto = IParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(parcelEntity);
        return newParcelInfoDto;
    }
}
