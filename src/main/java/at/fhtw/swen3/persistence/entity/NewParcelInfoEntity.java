package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public class NewParcelInfoEntity {

    @JsonProperty("trackingId")
    private String trackingId;

    public NewParcelInfoEntity() {
    }
    public NewParcelInfoEntity(String trackingId) {
        this.trackingId = trackingId;
    }
    public NewParcelInfoEntity trackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    /**
     * The tracking ID of the parcel.
     * @return trackingId
     */
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
        NewParcelInfoEntity newParcelInfo = (NewParcelInfoEntity) o;
        return Objects.equals(this.trackingId, newParcelInfo.trackingId);
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
