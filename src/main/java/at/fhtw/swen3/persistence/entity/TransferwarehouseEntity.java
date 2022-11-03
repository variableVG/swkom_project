package at.fhtw.swen3.persistence.entity;


import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Transferwarehouse")
public class TransferwarehouseEntity extends HopEntity {

    @JsonProperty("regionGeoJson")
    private String regionGeoJson;

    @JsonProperty("logisticsPartner")
    private String logisticsPartner;

    @JsonProperty("logisticsPartnerUrl")
    private String logisticsPartnerUrl;


    public TransferwarehouseEntity regionGeoJson(String regionGeoJson) {
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

    public TransferwarehouseEntity logisticsPartner(String logisticsPartner) {
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

    public TransferwarehouseEntity logisticsPartnerUrl(String logisticsPartnerUrl) {
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

    public TransferwarehouseEntity hopType(String hopType) {
        super.setHopType(hopType);
        return this;
    }

    public TransferwarehouseEntity code(String code) {
        super.setCode(code);
        return this;
    }

    public TransferwarehouseEntity description(String description) {
        super.setDescription(description);
        return this;
    }

    public TransferwarehouseEntity processingDelayMins(Integer processingDelayMins) {
        super.setProcessingDelayMins(processingDelayMins);
        return this;
    }

    public TransferwarehouseEntity locationName(String locationName) {
        super.setLocationName(locationName);
        return this;
    }

    public TransferwarehouseEntity locationCoordinates(GeoCoordinateEntity locationCoordinates) {
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
        TransferwarehouseEntity transferwarehouse = (TransferwarehouseEntity) o;
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
