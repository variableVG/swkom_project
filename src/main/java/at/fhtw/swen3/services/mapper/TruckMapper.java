package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TruckMapper {
    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);

    @Mapping(source= "truckDto.numberPlate", target="numberPlate")
    @Mapping(source= "truckDto.regionGeoJson", target="regionGeoJson")
    TruckEntity dtoToEntity(Truck truckDto);

    @Mapping(source= "truckEntity.numberPlate", target="numberPlate")
    @Mapping(source= "truckEntity.regionGeoJson", target="regionGeoJson")
    Truck entityToDto(TruckEntity truckEntity);
}
