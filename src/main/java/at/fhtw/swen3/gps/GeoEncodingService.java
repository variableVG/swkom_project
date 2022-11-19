package at.fhtw.swen3.gps;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;

import java.io.IOException;
import java.net.MalformedURLException;

public interface GeoEncodingService {

    GeoCoordinateEntity encodeAddress(RecipientEntity r) throws IOException;
}
