package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import org.springframework.stereotype.Service;


public interface ParcelService {

    public NewParcelInfo submitParcel(Parcel parcel);
}