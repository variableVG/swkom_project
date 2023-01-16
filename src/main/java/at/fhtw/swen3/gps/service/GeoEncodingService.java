package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.springframework.data.geo.Polygon;


public interface GeoEncodingService {
    GeoCoordinateEntity encodeAddress(RecipientEntity r);
    public Polygon getRegion(String regionGeoJson);

}
