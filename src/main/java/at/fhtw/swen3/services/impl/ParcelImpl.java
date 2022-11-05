package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParcelImpl implements ParcelService {


    private final ParcelRepository repo;

    public ParcelImpl(ParcelRepository repo) {

        this.repo = repo;
    }

    @Override
    public NewParcelInfo submitParcel(Parcel parcel) {

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.parcelDtoToParcelEntity(parcel);
        //generate TrackingId:
        String trackingId = RandomStringUtils.randomAlphabetic(9);
        parcelEntity.setTrackingId(trackingId.toUpperCase());
        System.out.println("Trakcing id is " + parcelEntity.getTrackingId());
        ParcelEntity newParcelEntity = repo.save(parcelEntity);

        NewParcelInfo newParcelInfo = ParcelMapper.INSTANCE.parcelEntityToNewParcelInfoDto(newParcelEntity);

        return newParcelInfo;
    }


}
