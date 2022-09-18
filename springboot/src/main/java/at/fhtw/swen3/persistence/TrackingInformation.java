package at.fhtw.swen3.persistence;

import java.net.URI;
import java.util.Objects;
import at.fhtw.swen3.persistence.HopArrival;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * TrackingInformation
 */

@JsonTypeName("trackingInformation")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
public class TrackingInformation {

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
  private List<HopArrival> visitedHops = new ArrayList<>();

  @JsonProperty("futureHops")
  @Valid
  private List<HopArrival> futureHops = new ArrayList<>();

  public TrackingInformation state(StateEnum state) {
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

  public TrackingInformation visitedHops(List<HopArrival> visitedHops) {
    this.visitedHops = visitedHops;
    return this;
  }

  public TrackingInformation addVisitedHopsItem(HopArrival visitedHopsItem) {
    this.visitedHops.add(visitedHopsItem);
    return this;
  }

  /**
   * Hops visited in the past.
   * @return visitedHops
  */
  @NotNull @Valid 
  @Schema(name = "visitedHops", description = "Hops visited in the past.", required = true)
  public List<HopArrival> getVisitedHops() {
    return visitedHops;
  }

  public void setVisitedHops(List<HopArrival> visitedHops) {
    this.visitedHops = visitedHops;
  }

  public TrackingInformation futureHops(List<HopArrival> futureHops) {
    this.futureHops = futureHops;
    return this;
  }

  public TrackingInformation addFutureHopsItem(HopArrival futureHopsItem) {
    this.futureHops.add(futureHopsItem);
    return this;
  }

  /**
   * Hops coming up in the future - their times are estimations.
   * @return futureHops
  */
  @NotNull @Valid 
  @Schema(name = "futureHops", description = "Hops coming up in the future - their times are estimations.", required = true)
  public List<HopArrival> getFutureHops() {
    return futureHops;
  }

  public void setFutureHops(List<HopArrival> futureHops) {
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
    TrackingInformation trackingInformation = (TrackingInformation) o;
    return Objects.equals(this.state, trackingInformation.state) &&
        Objects.equals(this.visitedHops, trackingInformation.visitedHops) &&
        Objects.equals(this.futureHops, trackingInformation.futureHops);
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

