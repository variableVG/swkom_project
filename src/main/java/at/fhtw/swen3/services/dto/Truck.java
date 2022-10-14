package at.fhtw.swen3.services.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Truck
 */


@JsonTypeName("truck")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
public class Truck extends Hop {

  @JsonProperty("regionGeoJson")
  private String regionGeoJson;

  @JsonProperty("numberPlate")
  private String numberPlate;

  public Truck regionGeoJson(String regionGeoJson) {
    this.regionGeoJson = regionGeoJson;
    return this;
  }

  /**
   * GeoJSON (https://geojson.org/) of the area covered by the truck.
   * @return regionGeoJson
  */
  @NotNull 
  @Schema(name = "regionGeoJson", description = "GeoJSON (https://geojson.org/) of the area covered by the truck.", required = true)
  public String getRegionGeoJson() {
    return regionGeoJson;
  }

  public void setRegionGeoJson(String regionGeoJson) {
    this.regionGeoJson = regionGeoJson;
  }

  public Truck numberPlate(String numberPlate) {
    this.numberPlate = numberPlate;
    return this;
  }

  /**
   * The truck's number plate.
   * @return numberPlate
  */
  @NotNull 
  @Schema(name = "numberPlate", description = "The truck's number plate.", required = true)
  public String getNumberPlate() {
    return numberPlate;
  }

  public void setNumberPlate(String numberPlate) {
    this.numberPlate = numberPlate;
  }

  public Truck hopType(String hopType) {
    super.setHopType(hopType);
    return this;
  }

  public Truck code(String code) {
    super.setCode(code);
    return this;
  }

  public Truck description(String description) {
    super.setDescription(description);
    return this;
  }

  public Truck processingDelayMins(Integer processingDelayMins) {
    super.setProcessingDelayMins(processingDelayMins);
    return this;
  }

  public Truck locationName(String locationName) {
    super.setLocationName(locationName);
    return this;
  }

  public Truck locationCoordinates(GeoCoordinate locationCoordinates) {
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
    Truck truck = (Truck) o;
    return Objects.equals(this.regionGeoJson, truck.regionGeoJson) &&
        Objects.equals(this.numberPlate, truck.numberPlate) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regionGeoJson, numberPlate, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Truck {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    regionGeoJson: ").append(toIndentedString(regionGeoJson)).append("\n");
    sb.append("    numberPlate: ").append(toIndentedString(numberPlate)).append("\n");
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

