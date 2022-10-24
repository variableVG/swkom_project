package at.fhtw.swen3.persistence.entity;


import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.core.util.Json;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class ParcelEntity {

    // 3 dtos 3 Mapper and

    //private ParcelModelEntity parcelModelEntity;
    //private TrackingInformationEntity trackingInformationEntity;
    //private NewParcelInfoEntity newParcelInfoEntity;

    private Float weight;
    private Recipient recipient;
    private Recipient sender;
    private String trackingId;

    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
    private List<HopArrivalEntity> futureHops = new ArrayList<>();


    /**
     * State of the parcel.
     */
    public enum StateEnum {
        PICKUP("Pickup"),

        INTRANSPORT("InTransport"),

        INTRUCKDELIVERY("InTruckDelivery"),

        TRANSFERRED("Transferred"),

        DELIVERED("Delivered");

        private String value;

        StateEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StateEnum fromValue(String value) {
            for (StateEnum b : StateEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private StateEnum state;
    public ParcelEntity() {
        // this.parcel = parcel;

    }


    public void submitParcel() {
        //TODO set real newParcelInfoEntity in the DB
        this.setTrackingId("PYJRB4HZ7");


    }

    public void trackParcel(String trackingId) {
        this.setTrackingId(trackingId);
        //TODO get from DB trackingInformationEntity according to trackingID
        //////////////////////// HARD CODED FOR TESTING //////////////////////////////////////////////
        this.setState(StateEnum.fromValue("Pickup"));
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
        this.setFutureHops(futureHops);
        this.setVisitedHops(visitedHops);
        ///////////////////////////////////////////////////////////////////////////////////////////////
    }
}
