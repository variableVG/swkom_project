package at.fhtw.swen3.gps.impl;

import at.fhtw.swen3.gps.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import io.swagger.models.Response;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.apache.tomcat.jni.Address;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;


public class BingEncodingProxy implements GeoEncodingService {

    @Override
    public GeoCoordinateEntity encodeAddress(RecipientEntity r) throws IOException {
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

        RestTemplate restTemplate = new RestTemplate();
        JSONObject j = restTemplate.getForObject(uri.toString(), JSONObject.class);
        System.out.println(j.length());

        //Source:
        // https://www.baeldung.com/java-http-response-body-as-string

        return null;
    }
}
