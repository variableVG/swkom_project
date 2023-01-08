package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    public GeoCoordinateEntity encodeAddress(RecipientEntity r)  {

        // Create URI
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("nominatim.openstreetmap.org")
                .path("/search")
                .queryParam("street", r.getStreet())
                .queryParam("city", r.getCity())
                .queryParam("postalcode", r.getPostalCode())
                .queryParam("country", r.getCountry())
                .queryParam("format", "json")
                .build();

        // Create HttpClient and send request
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest;
        HttpResponse response;

        try {
            log.debug("Class BingEncodingProxy, encodeAddress() - Send request for coordinates.");
            httpRequest = HttpRequest.newBuilder().uri(new URI(uri.toString())).build();
        }
        catch(Exception e) {
            log.warn("Class BingEncodingProxy, encodeAddress() - " + e);
            return null;
        }

        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.warn("Class BingEncodingProxy, httpRequest - " + e);
            throw new RuntimeException(e);
        }

        //parse response: (we get an array with a JSON)
        String json_string = response.body().toString();
        JSONArray temp1;
        JSONObject temp2;
        try {
            temp1 = new JSONArray(json_string);
            temp2 = new JSONObject(temp1.get(0).toString());
        } catch (JSONException e) {
            log.warn("Class BingEncodingProxy, Parsing httpResponse failed - " + e);
            throw new RuntimeException(e);
        }


        //Get lat and lon and create GeoCoordinate Class
        try {
            double lat = Double.parseDouble(temp2.get("lat").toString());
            double lon = Double.parseDouble(temp2.get("lon").toString());
            return GeoCoordinateEntity.builder().lat(lat).lon(lon).build();
        } catch (JSONException e) {
            log.warn("Class BingEncodingProxy, Coordinates not found - " + e);
            throw new RuntimeException(e);
        }

    }

}


