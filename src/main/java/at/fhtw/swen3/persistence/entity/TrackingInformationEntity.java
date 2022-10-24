package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.TrackingInformation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TrackingInformation
 */
@Data
@JsonTypeName("trackingInformation")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
public class TrackingInformationEntity {


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

  @JsonProperty("state")
  private StateEnum state;

  @JsonProperty("visitedHops")
  @Valid
  private List<HopArrivalEntity> visitedHops = new ArrayList<>();

  @JsonProperty("futureHops")
  @Valid
  private List<HopArrivalEntity> futureHops = new ArrayList<>();

  public TrackingInformationEntity state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * State of the parcel.
   * @return state
  */
  @NotNull 
  @Schema(name = "state", description = "State of the parcel.", required = true)
  public StateEnum getState() {
    return state;
  }


  public void setState(StateEnum state) {
    this.state = state;
  }

  public TrackingInformationEntity visitedHops(List<HopArrivalEntity> visitedHops) {
    this.visitedHops = visitedHops;
    return this;
  }

  public TrackingInformationEntity addVisitedHopsItem(HopArrivalEntity visitedHopsItem) {
    this.visitedHops.add(visitedHopsItem);
    return this;
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

  public TrackingInformationEntity futureHops(List<HopArrivalEntity> futureHops) {
    this.futureHops = futureHops;
    return this;
  }

  public TrackingInformationEntity addFutureHopsItem(HopArrivalEntity futureHopsItem) {
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrackingInformationEntity trackingInformationEntity = (TrackingInformationEntity) o;
    return Objects.equals(this.state, trackingInformationEntity.state) &&
        Objects.equals(this.visitedHops, trackingInformationEntity.visitedHops) &&
        Objects.equals(this.futureHops, trackingInformationEntity.futureHops);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, visitedHops, futureHops);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TrackingInformation {\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    visitedHops: ").append(toIndentedString(visitedHops)).append("\n");
    sb.append("    futureHops: ").append(toIndentedString(futureHops)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

