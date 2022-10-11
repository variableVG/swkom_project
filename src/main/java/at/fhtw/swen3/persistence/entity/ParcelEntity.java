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

    @NotNull(message = "PostalCode cannot be null")
    @Size(min = 4, max = 4, message = "A valid PostalCode must contain (A-, 4 digits, 0000-9999) ")
    private int PostalCode;

    //private ParcelDTO parcelDTO;

    public ParcelEntity() {
        //this.parcelDTO = parcelDTO;

    }


}
