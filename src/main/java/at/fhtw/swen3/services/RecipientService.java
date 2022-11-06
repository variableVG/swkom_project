package at.fhtw.swen3.services;

import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecipientService {
    @Autowired
    private RecipientMapper recipientMapper;
    @Autowired
    private MyValidator myValidator;

}
