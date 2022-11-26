package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
        //Map parcel to Entity
        //ParcelEntity parcelEntity = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);

        //generate TrackingId:
        String trackingId = RandomStringUtils.randomAlphabetic(9);
        parcelEntity.setTrackingId(trackingId.toUpperCase());
        parcelEntity.setState(ParcelEntity.StateEnum.TRANSFERRED);
        parcelEntity.setFutureHops(new ArrayList<>());
        parcelEntity.setVisitedHops(new ArrayList<>());


        //Save parcel in DB (repository function gives back a new object of the same class)
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

        return newParcelInfo;
    }

    @Override
    public long submitRecipient(Recipient recipient) throws Exception {
        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(recipient);
        try {
            RecipientEntity savedRecipient = recipientRepository.save(recipientEntity);
            return savedRecipient.getId();

        }  catch (Exception e){
            log.error("class submitRecipient, submitRecipient {}" ,e.getMessage());
            throw new Exception("The address of sender or receiver was not found.");
        }

    }


}
