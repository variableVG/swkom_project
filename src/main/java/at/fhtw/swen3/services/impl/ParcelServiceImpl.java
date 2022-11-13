package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {


    private final ParcelRepository repo;

    //@Autowired
    public RecipientRepository recipientRepository;
    private MyValidator myValidator;

    public ParcelServiceImpl(ParcelRepository repo) {
        this.repo = repo;
    }


    public NewParcelInfo submitParcel(Parcel parcel) {
        //Map parcel to Entity
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);

        //generate TrackingId:
        String trackingId = RandomStringUtils.randomAlphabetic(9);
        parcelEntity.setTrackingId(trackingId.toUpperCase());
        parcelEntity.setState(ParcelEntity.StateEnum.PICKUP);
        parcelEntity.setFutureHops(new ArrayList<>());
        parcelEntity.setVisitedHops(new ArrayList<>());



        //Sender and Recipient must first be saved.

        // System.out.println("Recipient is "  + parcelEntity.getRecipient().getName());
        log.info("Recipient is "  + parcelEntity.getRecipient().getName());
        RecipientEntity recipient = recipientRepository.save(parcelEntity.getRecipient());
        // System.out.println("Recipient is "  + recipient.getName());
        log.info("Recipient is "  + recipient.getName());
        //System.out.println("Sender is "  + parcelEntity.getSender().getName());
        log.info("Sender is "  + parcelEntity.getSender().getName());
        RecipientEntity sender = recipientRepository.save(parcelEntity.getSender());
        // System.out.println("Recipient and sender were stored.");
        log.info("Recipient and sender were stored.");



        //Save parcel in DB (repository function gives back a new object of the same class)
        ParcelEntity newParcelEntity = null;

        try {

            newParcelEntity = repo.save(parcelEntity);
        } catch (Exception e){
            log.error("class ParcelServiceImpl, submitParcel {}" ,e.getMessage());
            // e.printStackTrace();
        }

        //return what the API wants for us
        NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(newParcelEntity);

        return newParcelInfo;
    }


}
