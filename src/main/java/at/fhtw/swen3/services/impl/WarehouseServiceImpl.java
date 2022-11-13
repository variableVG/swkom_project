package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.validation.MyValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    private WarehouseRepository warehouseRepository;
    private MyValidator myValidator;
}
