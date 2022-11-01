package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.WarehouseEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    @Mapping(source= "nextHops", target="nextHops")
    @Mapping(source= "level", target="level")
    WarehouseEntity dtoToEntity(Warehouse warehouseDto);

    @Mapping(source= "nextHops", target="nextHops")
    @Mapping(source= "level", target="level")
    Warehouse entityToDto(WarehouseEntity warehouseEntity);
}
