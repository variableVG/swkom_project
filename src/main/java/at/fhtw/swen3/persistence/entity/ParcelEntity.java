package at.fhtw.swen3.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
//import at.fhtw.swen3.services.dto.Recipient;
// import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Parcel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Min(value= 0, message = "weight must be equal or greater than zero")
    @JsonProperty("weight")
    private Float weight;
    @ManyToOne
    @JsonProperty("recipient")
    private RecipientEntity recipient;
    @ManyToOne
    @JsonProperty("sender")
    private RecipientEntity sender;
    @JsonProperty("trackingId")
    private String trackingId;

    @ManyToMany
    @JsonProperty("visitedHops")
    @Valid
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

    @ManyToMany
    @JsonProperty("futureHops")
    @Valid
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
        public static ParcelEntity.StateEnum fromValue(String value) {
            for (StateEnum b : StateEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    @JsonProperty("state")
    private StateEnum state;



    @Schema(name = "trackingId", example = "PYJRB4HZ6", description = "The tracking ID of the parcel. ", required = false)
    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    @NotNull @Valid
    @Schema(name = "sender", required = true)
    public RecipientEntity getSender() {
        return sender;
    }

    public void setSender(RecipientEntity sender) {
        this.sender = sender;
    }

    public void setState(ParcelEntity.StateEnum state) {
        this.state = state;
    }

    public List<HopArrivalEntity> visitedHops(List<HopArrivalEntity> visitedHops) {
        this.visitedHops = visitedHops;
        return this.visitedHops;
    }

    public List<HopArrivalEntity> addVisitedHopsItem(HopArrivalEntity visitedHopsItem) {
        this.visitedHops.add(visitedHopsItem);
        return this.visitedHops;
    }

    /**
     * Hops visited in the past.
     * @return visitedHops
     */
    @NotNull @Valid
    @Schema(name = "visitedHops", description = "Hops visited in the past.", required = true)
    public List<HopArrivalEntity> getVisitedHops() {
        return visitedHops;
    }

    public void setVisitedHops(List<HopArrivalEntity> visitedHops) {
        this.visitedHops = visitedHops;
    }

    public ParcelEntity futureHops(List<HopArrivalEntity> futureHops) {
        this.futureHops = futureHops;
        return this;
    }

    public ParcelEntity addFutureHopsItem(HopArrivalEntity futureHopsItem) {
        this.futureHops.add(futureHopsItem);
        return this;
    }

    /**
     * Hops coming up in the future - their times are estimations.
     * @return futureHops
     */
    @NotNull @Valid
    @Schema(name = "futureHops", description = "Hops coming up in the future - their times are estimations.", required = true)
    public List<HopArrivalEntity> getFutureHops() {
        return futureHops;
    }

    public void setFutureHops(List<HopArrivalEntity> futureHops) {
        this.futureHops = futureHops;
    }



    @Override
    public int hashCode() {
        return Objects.hash(trackingId);
    }

    @NotNull
    @Schema(name = "weight", required = true)
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @NotNull @Valid
    @Schema(name = "recipient", required = true)
    public RecipientEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(RecipientEntity recipient) {
        this.recipient = recipient;
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
