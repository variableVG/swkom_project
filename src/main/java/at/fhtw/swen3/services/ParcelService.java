package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;


public interface ParcelService {

    public NewParcelInfo submitParcel(ParcelEntity parcel) throws Exception;


    public long submitRecipient(Recipient recipient) throws Exception;


    public void reportParcelDelivery(String trackingId) throws BLException;


    public TrackingInformation trackParcel(String trackingId) throws BLException;

    public boolean checkIfParcelExists(String trackingId);

    public void reportParcelHop(String trackingId, String code);

    public boolean checkIfHopExists(String code);
}
