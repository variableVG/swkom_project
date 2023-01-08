package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.GeoCoordinateService;

import java.util.concurrent.CompletableFuture;

public interface GeoEncodingService {
    GeoCoordinateEntity encodeAddress(RecipientEntity r);

}
