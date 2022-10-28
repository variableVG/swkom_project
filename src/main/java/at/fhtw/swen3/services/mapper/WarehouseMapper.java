package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    @Mapping(source= "warehouseDto.nextHops", target="nextHops")
    @Mapping(source= "warehouseDto.level", target="level")
    WarehouseEntity dtoToEntity(Warehouse warehouseDto);

    @Mapping(source= "warehouseEntity.nextHops", target="nextHops")
    @Mapping(source= "warehouseEntity.level", target="level")
    Warehouse entityToDto(WarehouseEntity warehouseEntity);
}
