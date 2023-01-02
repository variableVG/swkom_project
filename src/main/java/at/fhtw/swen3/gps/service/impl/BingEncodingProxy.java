package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class BingEncodingProxy implements GeoEncodingService {
    @Override
    public CompletableFuture<Object> encodeAddress(RecipientEntity r)  {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("nominatim.openstreetmap.org")
                .path("/search")
                .queryParam("street", r.getStreet())
                .queryParam("city", r.getCity())
                .queryParam("postalcode", r.getPostalCode())
                .queryParam("country", r.getCountry())
                .queryParam("format", "geojson")
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest;

        try {
            log.debug("Class BingEncodingProxy, encodeAddress() - Send request for coordinates.");
            log.info("Inside first try");
            // System.out.println("Inside first try");
            httpRequest = HttpRequest.newBuilder().uri(new URI(uri.toString())).build();
        }
        catch(Exception e) {
            log.warn("Class BingEncodingProxy, encodeAddress() - " + e);
            return null;
        }


        HttpResponse response;
        try {
            System.out.println("Second try2");
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Response is " + response);
        // System.out.println("Response is");
        // System.out.println(response);
        log.info("Directions response is " + response.body());
        // System.out.println("Directions response is ");
        // System.out.println(response.body());

        //TODO Parse response and assig to coordinates


        return null;
    }

}


