package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.persistence.NewParcelInfo;
import at.fhtw.swen3.persistence.Parcel;
import at.fhtw.swen3.persistence.TrackingInformation;
import at.fhtw.swen3.services.dto.ParcelDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ParcelEntity {

    private Parcel parcel;
    private TrackingInformation trackingInformation;
    private NewParcelInfo newParcelInfo;


    //private ParcelDTO parcelDTO;

    public ParcelEntity() {
        // this.parcel = parcel;

    }


}
