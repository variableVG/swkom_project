package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = HopMapper.class)
public interface WarehouseNextHopsMapper {
    WarehouseNextHopsMapper INSTANCE = Mappers.getMapper(WarehouseNextHopsMapper.class);

    @Mapping(source= "hop", target="hop")
    @Mapping(source= "traveltimeMins", target="traveltimeMins")
    WarehouseNextHopsEntity dtoToEntity(WarehouseNextHops warehouseNextHopsDto);

    @Mapping(source= "hop", target="hop")
    @Mapping(source= "traveltimeMins", target="traveltimeMins")
    WarehouseNextHops entityToDto(WarehouseNextHopsEntity warehouseNextHopsEntity);
}
