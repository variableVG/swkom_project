package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="GeoCoordinate")
public class GeoCoordinateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("lat")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lat", nullable = false)
    private Double lat;

    @JsonProperty("lon")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lon", nullable = false)
    private Double lon;

    public GeoCoordinateEntity lat(Double lat) {
        this.lat = lat;
        return this;
    }

    /**
     * Latitude of the coordinate.
     * @return lat
     */

    public GeoCoordinateEntity lon(Double lon) {
        this.lon = lon;
        return this;
    }

    /**
     * Longitude of the coordinate.
     * @return lon
     */


}



