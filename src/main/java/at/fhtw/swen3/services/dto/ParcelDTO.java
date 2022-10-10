package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.NewParcelInfo;
import at.fhtw.swen3.persistence.Parcel;
import at.fhtw.swen3.persistence.Recipient;
import at.fhtw.swen3.persistence.TrackingInformation;
import at.fhtw.swen3.services.ParcelApi;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
@Data
//@Component // Like this you can instantiate the parcelDTO
public class ParcelDTO {
    private Float weight;
    private Recipient recipient;
    private Recipient sender;

    private String trackingId;

    public ParcelDTO() {

    }


}
