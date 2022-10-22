package at.fhtw.swen3.services.impl;


import at.fhtw.swen3.persistence.entity.NewParcelInfoEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.ApiUtil;
import at.fhtw.swen3.services.ParcelApi;
import at.fhtw.swen3.services.mapper.IParcelMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;
import javax.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-18T13:20:22.807446Z[Etc/UTC]")
@Controller
@Component("parcel")
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;

    @Autowired
    public ParcelApiController(NativeWebRequest request) { this.request = request; }

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
    //@Autowired //Autowired might be needed to create inyection.
    public ResponseEntity<NewParcelInfo> submitParcel(
            @Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel
    ) {

        ParcelEntity parcelEntity = IParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);
        parcelEntity.submitParcel();
        NewParcelInfo newParcelInfoDto = IParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(parcelEntity);

        /*
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", newParcelInfoDto.toString());
                    break;
                }
            }
        });
        ResponseEntity<NewParcelInfo> answer = new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

         */

        return new ResponseEntity<NewParcelInfo>(newParcelInfoDto, HttpStatus.NOT_IMPLEMENTED);

    }
}
