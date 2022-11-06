package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParcelService {
    @Autowired
    private ParcelMapper parcelMapper;
    @Autowired
    private MyValidator myValidator;

    //public NewParcelInfo submitParcel(Parcel parcel);
}
