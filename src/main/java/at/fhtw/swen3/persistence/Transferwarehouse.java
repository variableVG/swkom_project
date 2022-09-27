package at.fhtw.swen3.persistence;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * Transferwarehouse
 */


@JsonTypeName("transferwarehouse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
public class Transferwarehouse extends Hop {

  @JsonProperty("regionGeoJson")
  private String regionGeoJson;

  @JsonProperty("logisticsPartner")
  private String logisticsPartner;

  @JsonProperty("logisticsPartnerUrl")
  private String logisticsPartnerUrl;

  public Transferwarehouse regionGeoJson(String regionGeoJson) {
    this.regionGeoJson = regionGeoJson;
    return this;
  }

  /**
   * GeoJSON (https://geojson.org/) of the area covered by the logistics partner.
   * @return regionGeoJson
  */
  @NotNull 
  @Schema(name = "regionGeoJson", description = "GeoJSON (https://geojson.org/) of the area covered by the logistics partner.", required = true)
  public String getRegionGeoJson() {
    return regionGeoJson;
  }

  public void setRegionGeoJson(String regionGeoJson) {
    this.regionGeoJson = regionGeoJson;
  }

  public Transferwarehouse logisticsPartner(String logisticsPartner) {
    this.logisticsPartner = logisticsPartner;
    return this;
  }

  /**
   * Name of the logistics partner.
   * @return logisticsPartner
  */
  @NotNull 
  @Schema(name = "logisticsPartner", description = "Name of the logistics partner.", required = true)
  public String getLogisticsPartner() {
    return logisticsPartner;
  }

  public void setLogisticsPartner(String logisticsPartner) {
    this.logisticsPartner = logisticsPartner;
  }

  public Transferwarehouse logisticsPartnerUrl(String logisticsPartnerUrl) {
    this.logisticsPartnerUrl = logisticsPartnerUrl;
    return this;
  }

  /**
   * BaseURL of the logistics partner's REST service.
   * @return logisticsPartnerUrl
  */
  @NotNull 
  @Schema(name = "logisticsPartnerUrl", description = "BaseURL of the logistics partner's REST service.", required = true)
  public String getLogisticsPartnerUrl() {
    return logisticsPartnerUrl;
  }

  public void setLogisticsPartnerUrl(String logisticsPartnerUrl) {
    this.logisticsPartnerUrl = logisticsPartnerUrl;
  }

  public Transferwarehouse hopType(String hopType) {
    super.setHopType(hopType);
    return this;
  }

  public Transferwarehouse code(String code) {
    super.setCode(code);
    return this;
  }

  public Transferwarehouse description(String description) {
    super.setDescription(description);
    return this;
  }

  public Transferwarehouse processingDelayMins(Integer processingDelayMins) {
    super.setProcessingDelayMins(processingDelayMins);
    return this;
  }

  public Transferwarehouse locationName(String locationName) {
    super.setLocationName(locationName);
    return this;
  }

  public Transferwarehouse locationCoordinates(GeoCoordinate locationCoordinates) {
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
    Transferwarehouse transferwarehouse = (Transferwarehouse) o;
    return Objects.equals(this.regionGeoJson, transferwarehouse.regionGeoJson) &&
        Objects.equals(this.logisticsPartner, transferwarehouse.logisticsPartner) &&
        Objects.equals(this.logisticsPartnerUrl, transferwarehouse.logisticsPartnerUrl) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regionGeoJson, logisticsPartner, logisticsPartnerUrl, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transferwarehouse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    regionGeoJson: ").append(toIndentedString(regionGeoJson)).append("\n");
    sb.append("    logisticsPartner: ").append(toIndentedString(logisticsPartner)).append("\n");
    sb.append("    logisticsPartnerUrl: ").append(toIndentedString(logisticsPartnerUrl)).append("\n");
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

