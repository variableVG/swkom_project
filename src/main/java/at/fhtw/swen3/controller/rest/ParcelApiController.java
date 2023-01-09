package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
@Controller
//@Component("parcel")
@Slf4j
public class ParcelApiController implements ParcelApi {

    @Autowired
    private ParcelService parcelImpl;
    @Autowired
    public RecipientRepository recipientRepository;

    private final NativeWebRequest request;

    @Autowired
    public ParcelApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    /**
     * POST /parcel : Submit a new parcel to the logistics service.
     *
     * @param parcel  (required)
     * @return Successfully submitted the new parcel (status code 201)
     *         or The operation failed due to an error. (status code 400)
     *         or The address of sender or receiver was not found. (status code 404)
     */
    @Operation(
            operationId = "submitParcel",
            summary = "Submit a new parcel to the logistics service. ",
            tags = { "sender" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully submitted the new parcel", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NewParcelInfo.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "The address of sender or receiver was not found.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    @Override
    //@Autowired //Autowired might be needed to create injection.
    public ResponseEntity<NewParcelInfo> submitParcel(
            @Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel
    ) {
        NewParcelInfo newParcelInfo = null;
        long recipient_id = -1;
        long sender_id = -1;
        try {
            recipient_id = parcelImpl.submitRecipient(parcel.getRecipient());
            sender_id = parcelImpl.submitRecipient(parcel.getSender());
        } catch (Exception e) {
            log.error("The address of sender or receiver was not found: "  + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            ParcelEntity parcelEntity = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);
            System.out.println("ParcelEntity has been mapped, weight is " + parcelEntity.getWeight());
            parcelEntity.getRecipient().setId(recipient_id);
            parcelEntity.getSender().setId(sender_id);
            newParcelInfo = parcelImpl.submitParcel(parcelEntity);
        } catch (Exception e) {
            log.error("The operation failed due to an error: "  + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Status for codes: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html
        return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);

    }


    /**
     * GET /parcel/{trackingId} : Find the latest state of a parcel by its tracking ID.
     *
     * @param trackingId The tracking ID of the parcel. E.g. PYJRB4HZ6  (required)
     * @return Parcel exists, here&#39;s the tracking information. (status code 200)
     *         or The operation failed due to an error. (status code 400)
     *         or Parcel does not exist with this tracking ID. (status code 404)
     */
    @Operation(
            operationId = "trackParcel",
            summary = "Find the latest state of a parcel by its tracking ID. ",
            tags = { "recipient" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parcel exists, here's the tracking information.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TrackingInformation.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Parcel does not exist with this tracking ID.")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/parcel/{trackingId}",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<TrackingInformation> trackParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$")
            @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true)
            @PathVariable("trackingId") String trackingId
    ) {
        //TODO

        return new ResponseEntity<TrackingInformation>( HttpStatus.OK);
    }


    /**
     * POST /parcel/{trackingId} : Transfer an existing parcel into the system from the service of a logistics partner.
     *
     * @param trackingId The tracking ID of the parcel. E.g. PYJRB4HZ6  (required)
     * @param parcel  (required)
     * @return Successfully transitioned the parcel (status code 200)
     *         or The operation failed due to an error. (status code 400)
     *         or A parcel with the specified trackingID is already in the system. (status code 409)
     */
    @Operation(
            operationId = "transitionParcel",
            summary = "Transfer an existing parcel into the system from the service of a logistics partner. ",
            tags = { "logisticsPartner" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully transitioned the parcel", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NewParcelInfo.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    }),
                    @ApiResponse(responseCode = "409", description = "A parcel with the specified trackingID is already in the system.")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel/{trackingId}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(
            @Pattern(regexp = "^[A-Z0-9]{9}$")
            @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true)
            @PathVariable("trackingId") String trackingId,
            @Parameter(name = "Parcel", description = "", required = true)
            @Valid @RequestBody Parcel parcel
    ) {
        //TODO
        //NewParcelInfo newParcelInfo = new NewParcelInfo();
        //newParcelInfo.setTrackingId("VYORB4HZ6");
        return new ResponseEntity<NewParcelInfo>(HttpStatus.OK);

    }


    /**
     * POST /parcel/{trackingId}/reportHop/{code} : Report that a Parcel has arrived at a certain hop either Warehouse or Truck.
     *
     * @param trackingId The tracking ID of the parcel. E.g. PYJRB4HZ6  (required)
     * @param code The Code of the hop (Warehouse or Truck). (required)
     * @return Successfully reported hop. (status code 200)
     *         or Parcel does not exist with this tracking ID or hop with code not found.  (status code 404)
     *         or The operation failed due to an error. (status code 400)
     */
    @Operation(
            operationId = "reportParcelHop",
            summary = "Report that a Parcel has arrived at a certain hop either Warehouse or Truck. ",
            tags = { "staff" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully reported hop."),
                    @ApiResponse(responseCode = "404", description = "Parcel does not exist with this tracking ID or hop with code not found. "),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel/{trackingId}/reportHop/{code}",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<Void> reportParcelHop(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId,
            @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$") @Parameter(name = "code", description = "The Code of the hop (Warehouse or Truck).", required = true) @PathVariable("code") String code
    ) {
        //TODO
        return new ResponseEntity<>(HttpStatus.OK);
    }



    /**
     * POST /parcel/{trackingId}/reportDelivery/ : Report that a Parcel has been delivered at it&#39;s final destination address.
     *
     * @param trackingId The tracking ID of the parcel. E.g. PYJRB4HZ6  (required)
     * @return Successfully reported hop. (status code 200)
     *         or The operation failed due to an error. (status code 400)
     *         or Parcel does not exist with this tracking ID.  (status code 404)
     */
    @Operation(
            operationId = "reportParcelDelivery",
            summary = "Report that a Parcel has been delivered at it's final destination address. ",
            tags = { "staff" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully reported hop."),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Parcel does not exist with this tracking ID. ")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/parcel/{trackingId}/reportDelivery/",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<Void> reportParcelDelivery(
            @Pattern(regexp = "^[A-Z0-9]{9}$")
            @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true)
            @PathVariable("trackingId") String trackingId
    ) {

        //TODO reportParcelDelivery

        return new ResponseEntity<>(HttpStatus.OK);

    }



}


