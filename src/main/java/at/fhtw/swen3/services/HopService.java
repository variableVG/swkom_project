package at.fhtw.swen3.services;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HopService {
    @Autowired
    private HopMapper hopMapper;

        @Autowired
    private MyValidator myValidator;
}
