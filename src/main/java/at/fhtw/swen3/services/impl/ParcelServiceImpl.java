package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private final ParcelRepository parcelRepository;
    @Autowired
    public RecipientRepository recipientRepository;
    @Autowired
    public HopRepository hopRepository;
    @Autowired
    public TruckRepository truckRepository;
    @Autowired
    public TransferwarehouseRepository transferwarehouseRepository;
    @Autowired
    public HopArrivalRepository hopArrivalRepository;

    @Autowired
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

    private List<HopEntity> getPath(HopEntity start, HopEntity end) {

        //First get path to root:
        List<HopEntity> hops = getPathToRoot(start);

        //Then get path from root to hopEntity-end
        HopEntity root = hops.get(hops.size()-1);
        hops.addAll(getPathToTruck(root, end));

        return hops;

    }

    private List<HopEntity> getPathToRoot(HopEntity hop) {
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

    private List<HopEntity> getPathToTruck(HopEntity start, HopEntity end) {
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

    public List<HopEntity> predictFutureHops(GeoCoordinateEntity senderCoordinate, GeoCoordinateEntity recipientCoordinate) {

        // 1. Find the nearest hop to sender and recipient
        HopEntity nearestHopToSender = findNearestHop(senderCoordinate.getLat(), senderCoordinate.getLon());
        HopEntity nearestHopToRecipient = findNearestHop(recipientCoordinate.getLat(), recipientCoordinate.getLon());

        // 2. Get path to the top warehouse
        /** We go up in the pyramid/tree until the root, for that we need to find the previous hop.
         * We do that looking in the warehouse_next_hops table, where the next_hop_id is now the current hop-id
         * and the warehouse_id the previous hop-id, since we try to climb up the tree.
         * */

        List<HopEntity> futureHopsEntities = getPath(nearestHopToSender, nearestHopToRecipient);

        return futureHopsEntities;
    }


    public String createUniqueTrackingId() {
        String trackingId = RandomStringUtils.randomAlphabetic(9).toUpperCase();
        ParcelEntity parcel = parcelRepository.findDistinctFirstByTrackingId(trackingId);
        //System.out.println("Parcel is " + parcel.toString());
        if (parcel != null) {
            createUniqueTrackingId();
        }
        return trackingId;
    }

    public NewParcelInfo submitParcel(ParcelEntity parcelEntity) throws Exception {

        //1. Create new unique Tracking ID (if parcel does not have a trackingId or if it has one but it has been already assigned to another parcel.
        if((parcelEntity.getTrackingId() == null) | (checkIfParcelExists(parcelEntity.getTrackingId())) ) {
            String trackingId = createUniqueTrackingId();
            parcelEntity.setTrackingId(trackingId.toUpperCase());
            log.info("Setting TrackingId " + parcelEntity.getTrackingId());
        }


        // 2.Get GPS coordinates for package sender/recipient
        log.info("Getting Geocoordinates");
        GeoCoordinateEntity senderCoordinates = geoEncodingService.encodeAddress(parcelEntity.getSender());
        GeoCoordinateEntity recipientCoordinates = geoEncodingService.encodeAddress(parcelEntity.getRecipient());
        recipientCoordinates.setCoordinates(); senderCoordinates.setCoordinates();

        // 3. Predict Future Hops (route btw sender --> recipient)
        List<HopEntity> futureHopsEntities = predictFutureHops(senderCoordinates, recipientCoordinates);
        List<HopArrivalEntity> futureHops = new ArrayList<>();
        //Transform the HopEntities in HopArrivalEntities
        for(HopEntity h : futureHopsEntities) {
            HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder().code(h.getCode()).visited(false).description(h.getDescription()).build();
            hopArrivalEntity.setParcel(parcelEntity);
            futureHops.add(hopArrivalEntity);
        }
        parcelEntity.setFutureHops(futureHops);
        parcelEntity.setVisitedHops(new ArrayList<>());


        // 4. Set parcel state to PickUP
        log.info("Setting state to PICKUP");
        parcelEntity.setState(ParcelEntity.StateEnum.PICKUP);

        // 5. After everything for the ParcelEntity is set, validate before saving to the Database
        myValidator.validate(parcelEntity);
        // 6. Save parcel in DB (repository function gives back a new object of the same class)
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
            newParcelEntity = parcelRepository.save(parcelEntity);
            //Save also the HopArrivals:
            for(HopArrivalEntity h : parcelEntity.getFutureHops()) {
                h.setParcel(newParcelEntity);
                hopArrivalRepository.save(h);
            }

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
     public void reportParcelDelivery(String trackingId) throws BLException {

        myValidator.validate(trackingId);

        try {
            // Get Parcel:
            ParcelEntity parcel = parcelRepository.findDistinctFirstByTrackingId(trackingId);
            //Change State of Parcel
            parcel.setState(ParcelEntity.StateEnum.DELIVERED);
            parcelRepository.updateParcelStatus(parcel.getId(), parcel.getState().name());
            log.info("The state of parcel has been changed to Delivered");

        } catch (Exception e) {
            throw new BLException(2L, "Failed to change state to delivered for Parcel " + trackingId + " : ",  e);
        }


    }

    @Override
    public TrackingInformation trackParcel(String trackingId) throws BLException {

        myValidator.validate(trackingId);

        TrackingInformation trackingInformation;
        try {
            //System.out.println("Start function");
            ParcelEntity parcelEntity = parcelRepository.findDistinctFirstByTrackingId(trackingId);
            log.info("parcel found " + parcelEntity.getId());
            trackingInformation = ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);
            // System.out.println("Mapper done");
        } catch (Exception e) {
            throw new BLException(1L, "Parcel could not be tracked. " , e);
        }
        //return what the API wants for us
        return trackingInformation;
    }

    @Override
    public boolean checkIfParcelExists(String trackingId) {
        ParcelEntity parcelEntity = parcelRepository.findDistinctFirstByTrackingId(trackingId);
        if (parcelEntity == null || trackingId == null) { return false; }
        return true;
    }

    public boolean checkIfHopExists(String code) {
        HopEntity hopEntity = hopRepository.findDistinctFirstByCode(code);
        if (hopEntity == null || code == null) { return false; }
        return true;
    }

    @Override
    public void reportParcelHop(String trackingId, String code) {
        // 1. Validate Data
        myValidator.validate(trackingId);
        myValidator.validate(code);

        // 2. Get Hop
        HopEntity hop = hopRepository.findDistinctFirstByCode(code);

        // 3. Get Parcel
        ParcelEntity parcel = parcelRepository.findDistinctFirstByTrackingId(trackingId);


        // 4. Remove the corresponding Hop from future hops of the parcel, and add it to visited hops of parcel.
        ListIterator<HopArrivalEntity> iter = parcel.getFutureHops().listIterator();
        while(iter.hasNext()){
            HopArrivalEntity h = iter.next();
            if(h.getCode().equals(hop.getCode())){
                h.setVisited(true);
                parcel.getVisitedHops().add(h);
                iter.remove();
            }
        }


        //5. Update state of Parcel:
            // Hop is Warehouse --> Change state to "InTransport"
            // Hop is Truck --> "InTruckDelivery"
            // Hop is Transferwarehouse --> "Transferred"
                // Call logistic partner API - TRANSFER (URL is part of TransferWarehouseData)

        switch (hop.getHopType().toLowerCase()) {
            case "warehouse" -> parcel.setState(ParcelEntity.StateEnum.INTRANSPORT);
            case "truck" -> parcel.setState(ParcelEntity.StateEnum.INTRUCKDELIVERY);
            case "transferwarehouse" -> parcel.setState(ParcelEntity.StateEnum.TRANSFERRED);
        }

        // 6. Update parcel back to the database.
        parcelRepository.updateParcelStatus(parcel.getId(), parcel.getState().name());
        transferwarehouseRepository.updateHopAsVisited(parcel.getId(), code);

    }


}
