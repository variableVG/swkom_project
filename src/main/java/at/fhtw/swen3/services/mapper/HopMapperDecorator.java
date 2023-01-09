package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;

public abstract class HopMapperDecorator implements HopMapper {
    private final HopMapper delegate;

    protected HopMapperDecorator(HopMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public HopEntity dtoToEntity(Hop hop) {
        if (hop instanceof Transferwarehouse transferwarehouse) {
            return TransferwarehouseMapper.INSTANCE.dtoToEntity(transferwarehouse);
        } else if (hop instanceof Truck truck) {
            return TruckMapper.INSTANCE.dtoToEntity(truck);
        } else if (hop instanceof Warehouse warehouse) {
            return WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        }
        return delegate.dtoToEntity(hop);
    }

    @Override
    public Hop entityToDto(HopEntity hop) {
        if (hop instanceof TransferwarehouseEntity transferwarehouse) {
            return TransferwarehouseMapper.INSTANCE.entityToDto(transferwarehouse);
        } else if (hop instanceof TruckEntity truck) {
            return TruckMapper.INSTANCE.entityToDto(truck);
        } else if (hop instanceof WarehouseEntity warehouse) {
            return WarehouseMapper.INSTANCE.entityToDto(warehouse);
        }
        return delegate.entityToDto(hop);
    }

}
