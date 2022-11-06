package at.fhtw.swen3.services;

import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HopArrivalService {
    @Autowired
    private HopArrivalMapper hopArrivalMapper;
    @Autowired
    private MyValidator myValidator;
}
