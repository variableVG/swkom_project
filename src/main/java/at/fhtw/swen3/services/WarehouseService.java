package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;

public interface WarehouseService {

    void importWarehouses(WarehouseEntity warehouse) throws BLException;

    WarehouseEntity exportWarehouses() throws BLException;

    HopEntity getWarehouse(String code) throws BLException;
}
