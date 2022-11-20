package at.fhtw.swen3.gps;

import at.fhtw.swen3.persistence.entities.RecipientEntity;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface GeoEncodingService {

    CompletableFuture<Object> encodeAddress(RecipientEntity r) throws IOException;
}
