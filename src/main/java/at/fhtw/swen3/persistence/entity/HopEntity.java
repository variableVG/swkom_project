package at.fhtw.swen3.persistence.entity;
import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;



@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //this solves the issue of not being able to create a warehouse table, but adds a "dtype" column to differentiate between the classes
//@DiscriminatorValue("hop") //this is needed because of @Inheritance, we can differentiate with this value, which class enters data in the table. May be added/changed for inheriting classes later.
// see: https://vladmihalcea.com/the-best-way-to-map-the-discriminatorcolumn-with-jpa-and-hibernate/
@Table(name="Hop")

public class HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("hopType")
    private String hopType;

    @JsonProperty("code")
    private String code;

    @Pattern(regexp = "[a-zA-ZÄÖÜäöüß0-9- ]+", message = "Warehouse-description must have only upper, lowercase letters, Numbers and -")
    @NotNull(message = "Warehouse-description cannot be null")
    @NotBlank(message = "Warehouse-description cannot be blank")
    @JsonProperty("description")
    private String description;

    @JsonProperty("processingDelayMins")
    private Integer processingDelayMins;

    @JsonProperty("locationName")
    private String locationName;

    @JsonProperty("locationCoordinates")
    @ManyToOne
    private GeoCoordinateEntity locationCoordinates;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public HopEntity hopType(String hopType) {
        this.hopType = hopType;
        return this;
    }

    /**
     * Get hopType
     * @return hopType
     */
    @NotNull
    @Schema(name = "hopType", required = true)
    public String getHopType() {
        return hopType;
    }

    public void setHopType(String hopType) {
        this.hopType = hopType;
    }

    public HopEntity code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Unique CODE of the hop.
     * @return code
     */
    @NotNull @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    @Schema(name = "code", description = "Unique CODE of the hop.", required = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HopEntity description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Description of the hop.
     * @return description
     */
    @NotNull
    @Schema(name = "description", description = "Description of the hop.", required = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HopEntity processingDelayMins(Integer processingDelayMins) {
        this.processingDelayMins = processingDelayMins;
        return this;
    }

    /**
     * Delay processing takes on the hop.
     * @return processingDelayMins
     */
    @NotNull
    @Schema(name = "processingDelayMins", description = "Delay processing takes on the hop.", required = true)
    public Integer getProcessingDelayMins() {
        return processingDelayMins;
    }

    public void setProcessingDelayMins(Integer processingDelayMins) {
        this.processingDelayMins = processingDelayMins;
    }

    public HopEntity locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    /**
     * Name of the location (village, city, ..) of the hop.
     * @return locationName
     */
    @NotNull
    @Schema(name = "locationName", description = "Name of the location (village, city, ..) of the hop.", required = true)
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public HopEntity locationCoordinates(GeoCoordinateEntity locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
        return this;
    }

    /**
     * Get locationCoordinates
     * @return locationCoordinates
     */
    @NotNull @Valid
    @Schema(name = "locationCoordinates", required = true)
    public GeoCoordinateEntity getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(GeoCoordinateEntity locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HopEntity hop = (HopEntity) o;
        return Objects.equals(this.hopType, hop.hopType) &&
                Objects.equals(this.code, hop.code) &&
                Objects.equals(this.description, hop.description) &&
                Objects.equals(this.processingDelayMins, hop.processingDelayMins) &&
                Objects.equals(this.locationName, hop.locationName) &&
                Objects.equals(this.locationCoordinates, hop.locationCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hopType, code, description, processingDelayMins, locationName, locationCoordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Hop {\n");
        sb.append("    hopType: ").append(toIndentedString(hopType)).append("\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    processingDelayMins: ").append(toIndentedString(processingDelayMins)).append("\n");
        sb.append("    locationName: ").append(toIndentedString(locationName)).append("\n");
        sb.append("    locationCoordinates: ").append(toIndentedString(locationCoordinates)).append("\n");
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
