package at.fhtw.swen3.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Truck")
//@DiscriminatorValue("truck")
public class TruckEntity extends HopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonProperty("regionGeoJson")
    @Column(columnDefinition="text")
    private String regionGeoJson;

    @JsonProperty("numberPlate")
    private String numberPlate;


    public TruckEntity regionGeoJson(String regionGeoJson) {
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

    public TruckEntity numberPlate(String numberPlate) {
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

    public TruckEntity hopType(String hopType) {
        super.setHopType(hopType);
        return this;
    }

    public TruckEntity code(String code) {
        super.setCode(code);
        return this;
    }

    public TruckEntity description(String description) {
        super.setDescription(description);
        return this;
    }

    public TruckEntity processingDelayMins(Integer processingDelayMins) {
        super.setProcessingDelayMins(processingDelayMins);
        return this;
    }

    public TruckEntity locationName(String locationName) {
        super.setLocationName(locationName);
        return this;
    }

    public TruckEntity locationCoordinates(GeoCoordinateEntity locationCoordinates) {
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
        TruckEntity truck = (TruckEntity) o;
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
