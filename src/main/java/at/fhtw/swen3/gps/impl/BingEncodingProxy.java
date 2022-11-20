package at.fhtw.swen3.gps.impl;

import at.fhtw.swen3.gps.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
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


        System.out.println(uri);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest;

        try {
            log.debug("Class BingEncodingProxy, encodeAddress() - Send request for coordinates.");
            System.out.println("Inside first try");
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
        System.out.println("Response is");
        System.out.println(response);
        System.out.println("Directions response is ");
        System.out.println(response.body());


        //RestTemplate restTemplate = new RestTemplate();
        //JSONObject j = restTemplate.getForObject(uri.toString(), JSONObject.class);
        //System.out.println(j.length());

        //Source:
        // https://www.baeldung.com/java-http-response-body-as-string

        return null;
    }


}
