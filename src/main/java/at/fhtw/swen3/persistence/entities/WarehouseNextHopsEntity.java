package at.fhtw.swen3.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="WarehouseNextHops") // why is it not created/resolved? makes no sense
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("traveltimeMins")
    private Integer traveltimeMins;

    @OneToOne
    @JoinColumn(name = "next_hop_id")
    @JsonProperty("hop")
    private HopEntity hop; // nextHop

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_id")
    @JsonProperty("warehouse")
    private HopEntity warehouse; // parent hop


    /*@ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonProperty("warehouse")
    private WarehouseEntity warehouse;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public WarehouseNextHopsEntity traveltimeMins(Integer traveltimeMins) {
        this.traveltimeMins = traveltimeMins;
        return this;
    }

    /**
     * Get traveltimeMins
     * @return traveltimeMins
     */
    @NotNull
    @Schema(name = "traveltimeMins", required = true)
    public Integer getTraveltimeMins() {
        return traveltimeMins;
    }

    public void setTraveltimeMins(Integer traveltimeMins) {
        this.traveltimeMins = traveltimeMins;
    }

    public WarehouseNextHopsEntity hop(HopEntity hop) {
        this.hop = hop;
        return this;
    }

    /**
     * Get hop
     * @return hop
     */
    @NotNull @Valid
    @Schema(name = "hop", required = true)
    public HopEntity getHop() {
        return hop;
    }

    public void setHop(HopEntity hop) {
        this.hop = hop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WarehouseNextHopsEntity warehouseNextHops = (WarehouseNextHopsEntity) o;
        return Objects.equals(this.traveltimeMins, warehouseNextHops.traveltimeMins) &&
                Objects.equals(this.hop, warehouseNextHops.hop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traveltimeMins, hop);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WarehouseAllOfNextHops {\n");
        sb.append("    traveltimeMins: ").append(toIndentedString(traveltimeMins)).append("\n");
        sb.append("    hop: ").append(toIndentedString(hop)).append("\n");
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
