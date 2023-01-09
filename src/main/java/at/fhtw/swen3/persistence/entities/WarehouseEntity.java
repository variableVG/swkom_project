package at.fhtw.swen3.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("warehouse")
@Table(name="Warehouse")
public class WarehouseEntity extends HopEntity {

    @JsonProperty("level")
    private Integer level;

    @OneToMany (mappedBy = "warehouse", cascade=CascadeType.PERSIST)
    @JsonProperty("nextHops")
    @Valid
    @NotNull
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

    //standard constructor for superclass type casting
    public WarehouseEntity(Long id, String hopType, String code, @Pattern(regexp = "[a-zA-ZÄÖÜäöüß0-9- ]+",
            message = "Warehouse-description must have only upper, lowercase letters, Numbers and -")
    @NotNull(message = "Warehouse-description cannot be null") @NotBlank(message = "Warehouse-description cannot be blank") String description,
                           Integer processingDelayMins, String locationName, GeoCoordinateEntity locationCoordinates, Integer level, List<WarehouseNextHopsEntity> nextHops) {
        super(id, hopType, code, description, processingDelayMins, locationName, locationCoordinates);
        this.level = level;
        this.nextHops = nextHops;
    }

    public WarehouseEntity level(Integer level) {
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

    public WarehouseEntity nextHops(List<WarehouseNextHopsEntity> nextHops) {
        this.nextHops = nextHops;
        return this;
    }

    public WarehouseEntity addNextHopsItem(WarehouseNextHopsEntity nextHopsItem) {
        this.nextHops.add(nextHopsItem);
        return this;
    }

    /**
     * Next hops after this warehouse (warehouses or trucks).
     * @return nextHops
     */
    @NotNull @Valid
    @Schema(name = "nextHops", description = "Next hops after this warehouse (warehouses or trucks).", required = true)
    public List<WarehouseNextHopsEntity> getNextHops() {
        return nextHops;
    }

    public void setNextHops(List<WarehouseNextHopsEntity> nextHops) {
        this.nextHops = nextHops;
    }

    public WarehouseEntity hopType(String hopType) {
        super.setHopType(hopType);
        return this;
    }

    public WarehouseEntity code(String code) {
        super.setCode(code);
        return this;
    }

    public WarehouseEntity description(String description) {
        super.setDescription(description);
        return this;
    }

    public WarehouseEntity processingDelayMins(Integer processingDelayMins) {
        super.setProcessingDelayMins(processingDelayMins);
        return this;
    }

    public WarehouseEntity locationName(String locationName) {
        super.setLocationName(locationName);
        return this;
    }

    public WarehouseEntity locationCoordinates(GeoCoordinateEntity locationCoordinates) {
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
        WarehouseEntity warehouse = (WarehouseEntity) o;
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
