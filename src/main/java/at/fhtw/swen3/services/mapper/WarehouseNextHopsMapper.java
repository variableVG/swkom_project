package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseNextHopsMapper {
    WarehouseNextHopsMapper INSTANCE = Mappers.getMapper(WarehouseNextHopsMapper.class);

    @Mapping(source= "warehouseNextHopsDto.hop", target="hop")
    @Mapping(source= "warehouseNextHopsDto.traveltimeMins", target="traveltimeMins")
    WarehouseNextHopsEntity dtoToEntity(WarehouseNextHops warehouseNextHopsDto);

    @Mapping(source= "warehouseNextHopsEntity.hop", target="hop")
    @Mapping(source= "warehouseNextHopsEntity.traveltimeMins", target="traveltimeMins")
    WarehouseNextHops entityToDto(WarehouseNextHopsEntity warehouseNextHopsEntity);
}
