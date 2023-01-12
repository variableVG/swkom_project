package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.BingEncodingProxy;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;

import javax.sound.midi.Track;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private final ParcelRepository repo;
    @Autowired
    public RecipientRepository recipientRepository;
    @Autowired
    public HopRepository hopRepository;
    @Autowired
    public TruckRepository truckRepository;
    @Autowired
    public TransferwarehouseRepository transferwarehouseRepository;


    private MyValidator myValidator;
    private GeoEncodingService geoEncodingService;

    public HopEntity findNearestHop (Double lat, Double lon) {
        /** This function returns the nearest Hop to a coordinate */

        HopEntity hop= hopRepository.findNearestHop(lat, lon);
        return hop;
    }

    private HopArrivalEntity generateHopArrivalFromHop(HopEntity hop) {
        return HopArrivalEntity.builder().code(hop.getCode()).description(hop.getDescription()).build();
    }


    List<HopEntity> getPath(HopEntity start, HopEntity end) {

        //First get path to root:
        List<HopEntity> hops = getPathToRoot(start);

        //Then get path from root to hopEntity-end
        HopEntity root = hops.get(hops.size()-1);
        hops.addAll(getPathToTruck(root, end));

        return hops;

    }
    List<HopEntity> getPathToRoot(HopEntity hop) {
        List<HopEntity> path = new ArrayList<>();
        path.add(hop);
        List<HopEntity> hops = hopRepository.getPreviousHops(hop.getId());
        if(hops.isEmpty()) { return path; }
        for(HopEntity h : hops) {
            if (h.getCode().equalsIgnoreCase("Root")) {
                return path;
            }
            else {
                List<HopEntity> tmp = getPathToRoot(h);
                if (tmp != null) {
                    path.addAll(tmp);
                    return path;
                }
            }
        }
        return null;
    }

    List<HopEntity> getPathToTruck(HopEntity start, HopEntity end) {
        List<HopEntity> path = new ArrayList<>();
        List<HopEntity> hops = hopRepository.getNextHops(start.getId());
        for(HopEntity h : hops) {
            if (Objects.equals(h.getId(), end.getId())) {
                path.add(end);
                return path;
            }
            else {
                List<HopEntity> tmp = getPathToTruck(h, end);
                if (tmp != null) {
                    path.add(h);
                    path.addAll(tmp);
                    return path;
                }
            }
        }
        return null;
    }
    public List<HopArrivalEntity> predictFutureHops(GeoCoordinateEntity senderCoordinate, GeoCoordinateEntity recipientCoordinate) {
        List<HopArrivalEntity> futureHops = new ArrayList<>();

        // 1. Find the nearest hop to sender and recipient
        HopEntity nearestHopToSender = findNearestHop(senderCoordinate.getLat(), senderCoordinate.getLon());
        futureHops.add(generateHopArrivalFromHop(nearestHopToSender));
        System.out.println("Id for nearestSenderHop is " + nearestHopToSender.getId() + " with code " + nearestHopToSender.getCode());
        HopEntity nearestHopToRecipient = findNearestHop(recipientCoordinate.getLat(), recipientCoordinate.getLon());
        System.out.println("Id for RecipientHop is " + nearestHopToSender.getId() + " with code " + nearestHopToRecipient.getCode());

        // 3. Get path to the top warehouse
        /** We go up in the pyramid/tree until the root, for that we need to find the previous hop.
         * We do that looking in the warehouse_next_hops table, where the next_hop_id is now the current hop-id
         * and the warehouse_id the previous hop-id, since we try to climb up the tree.
         * */
        /*
        HopEntity previousHop = nearestHopToRecipient;
        while(!previousHop.getLocationName().equalsIgnoreCase("Root")) {
            Long id = previousHop.getId();
            previousHop = hopRepository.getPreviousHops(id);
            futureHops.add(generateHopArrivalFromHop(previousHop));
        }*/

        List<HopEntity> futureHopsEntities = getPath(nearestHopToSender, nearestHopToRecipient);

        //Now go down until reaching the truck corresponding to the nearestHopToRecipient
        //futureHops.addAll(getPathToTruck(previousHop, nearestHopToRecipient));


        System.out.println("Previous Hpps size is " + futureHopsEntities.size());
        for(HopEntity h : futureHopsEntities) {
            System.out.println(h.getDescription() + " with code " + h.getCode());
        }




        // 4. Go down to truck for the recipient

        return futureHops;
    }

    public NewParcelInfo submitParcel(ParcelEntity parcelEntity) throws Exception {

        // 1. Validate parcel data

        log.info("Validating Parcel");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(parcelEntity);

        for (ConstraintViolation<ParcelEntity> violation : violations)
        {
            log.error("Validation failed: ");
            log.error(violation.getMessage());
        }



        //2. Create new unique Tracking ID
        //TODO: Make sure trakcing ID is unique
        String trackingId = RandomStringUtils.randomAlphabetic(9);
        parcelEntity.setTrackingId(trackingId.toUpperCase());
        log.info("Setting TrackingId " + parcelEntity.getTrackingId());


        // 3.Get GPS coordinates for package sender/recipient
        log.info("Getting Geocoordinates");
        GeoCoordinateEntity senderCoordinates = geoEncodingService.encodeAddress(parcelEntity.getSender());
        GeoCoordinateEntity recipientCoordinates = geoEncodingService.encodeAddress(parcelEntity.getRecipient());
        recipientCoordinates.setCoordinates(); senderCoordinates.setCoordinates();
        log.info("GeoCoordinates for Sender are " + senderCoordinates.getLat() + " , " + senderCoordinates.getLon());
        log.info("GeoCoordinates for Recipient are " + recipientCoordinates.getLat() + " , " + recipientCoordinates.getLon());


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
