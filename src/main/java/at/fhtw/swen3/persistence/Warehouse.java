package at.fhtw.swen3.persistence;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Warehouse
 */


@JsonTypeName("warehouse")
//@Pattern(regexp = "[a-zA-Z]+", message = "cityname must have only upper & lowercase letters")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
public class Warehouse extends Hop {

  @JsonProperty("level")
  private Integer level;

  @JsonProperty("nextHops")
  @Valid
  private List<WarehouseNextHops> nextHops = new ArrayList<>();

  public Warehouse level(Integer level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   * @return level
  */
  @NotNull 
  @Schema(name = "level", required = true)
  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Warehouse nextHops(List<WarehouseNextHops> nextHops) {
    this.nextHops = nextHops;
    return this;
  }

  public Warehouse addNextHopsItem(WarehouseNextHops nextHopsItem) {
    this.nextHops.add(nextHopsItem);
    return this;
  }

  /**
   * Next hops after this warehouse (warehouses or trucks).
   * @return nextHops
  */
  @NotNull @Valid 
  @Schema(name = "nextHops", description = "Next hops after this warehouse (warehouses or trucks).", required = true)
  public List<WarehouseNextHops> getNextHops() {
    return nextHops;
  }

  public void setNextHops(List<WarehouseNextHops> nextHops) {
    this.nextHops = nextHops;
  }

  public Warehouse hopType(String hopType) {
    super.setHopType(hopType);
    return this;
  }

  public Warehouse code(String code) {
    super.setCode(code);
    return this;
  }

  public Warehouse description(String description) {
    super.setDescription(description);
    return this;
  }

  public Warehouse processingDelayMins(Integer processingDelayMins) {
    super.setProcessingDelayMins(processingDelayMins);
    return this;
  }

  public Warehouse locationName(String locationName) {
    super.setLocationName(locationName);
    return this;
  }

  public Warehouse locationCoordinates(GeoCoordinate locationCoordinates) {
    super.setLocationCoordinates(locationCoordinates);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Warehouse warehouse = (Warehouse) o;
    return Objects.equals(this.level, warehouse.level) &&
        Objects.equals(this.nextHops, warehouse.nextHops) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, nextHops, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Warehouse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    nextHops: ").append(toIndentedString(nextHops)).append("\n");
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

