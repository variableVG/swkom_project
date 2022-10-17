package at.fhtw.swen3.persistence.entity;


import lombok.Builder;
import lombok.Data;


@Data
public class ParcelEntity {

    // 3 dtos 3 Mapper and

    private ParcelModelEntity parcelModelEntity;
    private TrackingInformationEntity trackingInformationEntity;
    private NewParcelInfoEntity newParcelInfoEntity;


    //private ParcelDTO parcelDTO;

    public ParcelEntity() {
        // this.parcel = parcel;

    }


}
