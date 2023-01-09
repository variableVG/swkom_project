package at.fhtw.swen3.services.mapper;


import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {GeoCoordinateMapper.class})
@DecoratedWith(HopMapperDecorator.class)
public interface HopMapper {
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);

    @Mapping(source= "locationName", target="locationName")
    @Mapping(source= "locationCoordinates", target="locationCoordinates")
    @Mapping(source= "processingDelayMins", target="processingDelayMins")
    @Mapping(source= "description", target="description")
    @Mapping(source= "code", target="code")
    @Mapping(source= "hopType", target="hopType")
    HopEntity dtoToEntity(Hop hopDto);

    @Mapping(source= "locationName", target="locationName")
    @Mapping(source= "locationCoordinates", target="locationCoordinates")
    @Mapping(source= "processingDelayMins", target="processingDelayMins")
    @Mapping(source= "description", target="description")
    @Mapping(source= "code", target="code")
    @Mapping(source= "hopType", target="hopType")
    Hop entityToDto(HopEntity hopEntity);
}
