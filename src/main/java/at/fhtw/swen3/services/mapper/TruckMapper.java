package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {GeoCoordinateMapper.class})
public interface TruckMapper {
    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);

    @Mapping(source= "numberPlate", target="numberPlate")
    @Mapping(source= "regionGeoJson", target="regionGeoJson")
    TruckEntity dtoToEntity(Truck truckDto);

    @Mapping(source= "numberPlate", target="numberPlate")
    @Mapping(source= "regionGeoJson", target="regionGeoJson")
    Truck entityToDto(TruckEntity truckEntity);
}
