package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.repository.GeoCoordinateRepository;
import at.fhtw.swen3.services.mapper.GeoCoordinateMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeoCoordinateService {
    @Autowired
    private GeoCoordinateMapper geoCoordinateMapper;
    @Autowired
    private MyValidator myValidator;
}
