package at.fhtw.swen3.persistence.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.geo.Point;

import javax.persistence.*;

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

    @JsonProperty("coordinate")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coordinate", nullable = false)
    private Point coordinate;


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

    public void setCoordinates() {
        this.coordinate = new Point(lat, lon);
    }




}



