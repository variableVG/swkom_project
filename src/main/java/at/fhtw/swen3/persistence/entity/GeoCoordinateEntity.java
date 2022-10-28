package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;
@Data
public class GeoCoordinateEntity {

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("lon")
    private Double lon;

    public GeoCoordinateEntity lat(Double lat) {
        this.lat = lat;
        return this;
    }

    /**
     * Latitude of the coordinate.
     * @return lat
     */
    @NotNull
    @Schema(name = "lat", description = "Latitude of the coordinate.", required = true)
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public GeoCoordinateEntity lon(Double lon) {
        this.lon = lon;
        return this;
    }

    /**
     * Longitude of the coordinate.
     * @return lon
     */
    @NotNull
    @Schema(name = "lon", description = "Longitude of the coordinate.", required = true)
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GeoCoordinateEntity geoCoordinate = (GeoCoordinateEntity) o;
        return Objects.equals(this.lat, geoCoordinate.lat) &&
                Objects.equals(this.lon, geoCoordinate.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GeoCoordinate {\n");
        sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
        sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
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



