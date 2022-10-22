package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.mock.MockCreationSettings;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.internal.util.MockUtil.createMock;
import static org.mockito.internal.util.MockUtil.createStaticMock;

public class ParcelApiControllerTest {
    ParcelApiController parcelApiController;

    @Test
    void submitParcelTest() {
        // Input: parceldto
        Recipient recipient = Recipient.builder().name("Violeta").street("Straße A")
                .postalCode("1200").city("Vienna").country("Austria").build();
        Recipient sender = Recipient.builder().name("Georg").street("Straße B")
                .postalCode("3000").city("Linz").country("Austria").build();
        Parcel parcel = Parcel.builder().weight(7.5F).sender(sender)
                        .recipient(recipient).build();

        //Generate request with a mock.
        HttpServletRequest request = new MockHttpServletRequest("POST", "/parcel");
        NativeWebRequest webRequest = new ServletWebRequest(request);
        parcelApiController = new ParcelApiController(webRequest);

        //Call Method
        ResponseEntity<NewParcelInfo> answer = parcelApiController.submitParcel(parcel);

        //Check results
        //TODO: the NewParcelInfo trackingId is hardcoded in the Controller function. This should be changed in the future.
        assertEquals(answer.getBody().getTrackingId(), "PYJRB4HZ7");

    }



}
