package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
//import at.fhtw.swen3.services.dto.Recipient;
// import at.fhtw.swen3.services.dto.TrackingInformation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="parcel")
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Min(value= 0, message = "weight must be equal or greater than zero")
    @JsonProperty("weight")
    private Float weight;
    @ManyToOne
    @JoinColumn(name="recipient_id", nullable=true)
    @JsonProperty("recipient")
    private RecipientEntity recipient;
    @ManyToOne
    @JoinColumn(name="sender_id", nullable=true)
    @JsonProperty("sender")
    private RecipientEntity sender;
    @JsonProperty("trackingId")
    @Column(unique=true)
    private String trackingId;

    @JsonProperty("visitedHops")
    @Where(clause = "visited = true")
    @OneToMany(mappedBy = "parcel",  cascade=CascadeType.PERSIST, fetch =  FetchType.EAGER)
    @Valid
    private List<HopArrivalEntity> visitedHops;

    @JsonProperty("futureHops")
    @Where(clause =  "visited = false")
    @OneToMany(mappedBy = "parcel", cascade=CascadeType.PERSIST, fetch =  FetchType.EAGER)
    @Valid
    private List<HopArrivalEntity> futureHops;


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
    @Enumerated(EnumType.STRING)
    private StateEnum state;


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


    @Override
    public int hashCode() {
        return Objects.hash(trackingId);
    }

}
