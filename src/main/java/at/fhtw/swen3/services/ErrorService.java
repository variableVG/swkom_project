package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import at.fhtw.swen3.services.mapper.ErrorMapper;
import at.fhtw.swen3.services.mapper.GeoCoordinateMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ErrorService {
    @Autowired
    private ErrorMapper errorMapper;

    @Autowired
    private MyValidator myValidator;

    /*
    public void saveNewBook(Error error) {
        myValidator.validate(error);
        // 1. map to entity
        ErrorEntity entity = ErrorMapper.mapToSource(error);
        // 2. save entity
        // ...
        log.info(entity.toString());
    }

     */

}
