package at.fhtw.swen3.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * NewParcelInfo
 */
@Data
@JsonTypeName("newParcelInfo")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
@Entity
public class NewParcelInfoEntity {

  @JsonProperty("trackingId")
  private String trackingId;

  public NewParcelInfoEntity trackingId(String trackingId) {
    this.trackingId = trackingId;
    return this;
  }

  /**
   * The tracking ID of the parcel. 
   * @return trackingId
  */
  @Pattern(regexp = "^[A-Z0-9]{9}$") 
  @Schema(name = "trackingId", example = "PYJRB4HZ6", description = "The tracking ID of the parcel. ", required = false)
  public String getTrackingId() {
    return trackingId;
  }

  public void setTrackingId(String trackingId) {
    this.trackingId = trackingId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewParcelInfoEntity newParcelInfoEntity = (NewParcelInfoEntity) o;
    return Objects.equals(this.trackingId, newParcelInfoEntity.trackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trackingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewParcelInfo {\n");
    sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
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

