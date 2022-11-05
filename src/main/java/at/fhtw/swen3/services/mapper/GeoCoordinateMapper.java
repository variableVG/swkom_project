package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;

import at.fhtw.swen3.services.dto.GeoCoordinate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper

public interface GeoCoordinateMapper {
    GeoCoordinateMapper INSTANCE = Mappers.getMapper(GeoCoordinateMapper.class);

    @Mapping(source= "lat", target="lat")
    @Mapping(source= "lon", target="lon")
    GeoCoordinateEntity dtoToEntity(GeoCoordinate geoCoordinate);


    @Mapping(source= "lat", target="lat")
    @Mapping(source= "lon", target="lon")
    GeoCoordinate entityToDto(GeoCoordinateEntity geoCoordinateEntity);
}
