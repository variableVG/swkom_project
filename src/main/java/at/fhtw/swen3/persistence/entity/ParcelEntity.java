package at.fhtw.swen3.persistence.entity;


import at.fhtw.swen3.services.dto.TrackingInformation;
import io.swagger.v3.core.util.Json;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


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

    public TrackingInformationEntity trackParcel(String trackingId) {
        this.trackingInformationEntity = new TrackingInformationEntity();
        //TODO get from DB trackingInformationEntity according to trackingID
        //////////////////////// HARD CODED FOR TESTING //////////////////////////////////////////////
        this.trackingInformationEntity.setState(TrackingInformationEntity.StateEnum.fromValue("Pickup"));
        HopArrivalEntity hopArrivalEntity1 = HopArrivalEntity.builder()
                .code("XWZQ5").description("description 1")
                .dateTime(OffsetDateTime.parse("2022-10-22T12:57:59.601Z")).build();
        HopArrivalEntity hopArrivalEntity2 = HopArrivalEntity.builder()
                .code("PXXS27").description("description 2")
                .dateTime(OffsetDateTime.parse("2022-10-22T12:57:59.601Z")).build();

        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        List<HopArrivalEntity> futureHops = new ArrayList<>();
        visitedHops.add(hopArrivalEntity1);
        futureHops.add(hopArrivalEntity2);
        this.trackingInformationEntity.setFutureHops(futureHops);
        this.trackingInformationEntity.setVisitedHops(visitedHops);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        return this.trackingInformationEntity;
    }
}
