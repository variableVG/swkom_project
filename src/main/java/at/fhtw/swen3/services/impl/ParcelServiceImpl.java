package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.BingEncodingProxy;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.midi.Track;
import java.util.ArrayList;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private final ParcelRepository repo;


    @Autowired
    public RecipientRepository recipientRepository;

    private MyValidator myValidator;



    public NewParcelInfo submitParcel(ParcelEntity parcelEntity) throws Exception {

        // 1. Validate parcel data
        /* Validation fails here, something is Null, but It is not indicated what.
        log.info("Validating Parcel");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(parcelEntity);

        for (ConstraintViolation<ParcelEntity> violation : violations)
        {
            log.error("Validation failed: ");
            log.error(violation.getMessage());
        }


         */
        //2. Create new unique Tracking ID
        //TODO: Make sure trakcing ID is unique
        String trackingId = RandomStringUtils.randomAlphabetic(9);
        parcelEntity.setTrackingId(trackingId.toUpperCase());
        log.info("Setting TrackingId " + parcelEntity.getTrackingId());


        // 3.Get GPS coordinates for package sender/recipient
        /* This part also does not work --> Bean for GeoCoordinates is not found
        log.info("Getting Geocoordinates");
        GeoEncodingService geoEncodingService = new BingEncodingProxy();
        GeoCoordinateEntity senderCoordinates = geoEncodingService.encodeAddress(parcelEntity.getSender());
        GeoCoordinateEntity recipientCoordinates = geoEncodingService.encodeAddress(parcelEntity.getRecipient());
        log.info("GeoCoordinates for Sender are " + senderCoordinates.getLat() + " , " + senderCoordinates.getLon());
        log.info("GeoCoordinates for Recipient are " + recipientCoordinates.getLat() + " , " + recipientCoordinates.getLon());


         */





        // 4. Predict Future Hops (route btw sender --> recipient)
        parcelEntity.setFutureHops(new ArrayList<>());
        parcelEntity.setVisitedHops(new ArrayList<>());


        // 5. Set parcel state to PickUP
        log.info("Setting state to PICKUP");
        parcelEntity.setState(ParcelEntity.StateEnum.PICKUP);


        //6. Save parcel in DB (repository function gives back a new object of the same class)
        ParcelEntity newParcelEntity = null;
        try {
            if (parcelEntity.getRecipient().getId() == null) {
                log.info("Recipient is "  + parcelEntity.getRecipient().getName());
                RecipientEntity recipient = recipientRepository.save(parcelEntity.getRecipient());
                parcelEntity.getRecipient().setId(recipient.getId());
            }
            if(parcelEntity.getSender().getId() == null) {
                log.info("Sender is "  + parcelEntity.getSender().getName());
                RecipientEntity sender = recipientRepository.save(parcelEntity.getSender());
                parcelEntity.getRecipient().setId(sender.getId());
            }
            log.info("Recipient and sender were stored.");
        }  catch (Exception e){
            log.error("class ParcelServiceImpl, submitParcel {}" ,e.getMessage());
            throw new Exception("The address of sender or receiver was not found.");
        }


        try {
            newParcelEntity = repo.save(parcelEntity);
        } catch (Exception e){
            log.error("class ParcelServiceImpl, submitParcel {}" ,e.getMessage());
            throw new Exception("The operation failed due to an error");
        }

        //return what the API wants for us
        NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(newParcelEntity);

        log.info("TrackingId of the new parcel" + parcelEntity.getTrackingId());
        return newParcelInfo;
    }

    @Override
    public NewParcelInfo transferParcel(ParcelEntity parcel) throws Exception{
        // ParcelRepository.findById(long id);


        return null;
    }

    @Override
    public long submitRecipient(Recipient recipient) throws Exception {
        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(recipient);
        try {
            RecipientEntity savedRecipient = recipientRepository.save(recipientEntity);
            log.info("Recipient with id " + savedRecipient.getId()+ " saved");
            return savedRecipient.getId();

        }  catch (Exception e){
            log.error("class submitRecipient, submitRecipient {}" ,e.getMessage());
            throw new Exception("The address of sender or receiver was not found.");
        }

    }

    @Override
     public void reportParcelDelivery(ParcelEntity parcelEntity)  {

        parcelEntity.setState(ParcelEntity.StateEnum.DELIVERED);
        repo.save(parcelEntity);
        log.info("The state of parcel has been changed to Delivered");

    }

    @Override
    public TrackingInformation trackParcel(String trackingId) {
        ParcelEntity parcelEntity = repo.findDistinctFirstByTrackingId(trackingId);

        //return what the API wants for us
        TrackingInformation trackingInformation = ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);
        return trackingInformation;
    }


}
