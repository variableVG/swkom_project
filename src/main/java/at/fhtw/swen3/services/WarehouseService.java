package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.Warehouse;

public interface WarehouseService {

    void importWarehouses(Warehouse warehouse) throws BLException;

}
