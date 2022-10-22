package at.fhtw.swen3.persistence.entity;


import io.swagger.v3.core.util.Json;
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

    public NewParcelInfoEntity submitParcel() {
        //TODO set real newParcelInfoEntity in the DB
        this.newParcelInfoEntity = new NewParcelInfoEntity();
        this.newParcelInfoEntity.setTrackingId("PYJRB4HZ7");
        return this.newParcelInfoEntity;

    }
}
