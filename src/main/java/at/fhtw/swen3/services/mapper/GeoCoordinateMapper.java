package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.HopArrival;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeoCoordinateMapper {
    GeoCoordinateMapper INSTANCE = Mappers.getMapper(GeoCoordinateMapper.class);

    @Mapping(source= "geoCoordinate.lat", target="lat")
    @Mapping(source= "geoCoordinate.lon", target="lon")
    GeoCoordinateEntity dtoToEntity(GeoCoordinate geoCoordinate);


    @Mapping(source= "geoCoordinateEntity.lat", target="lat")
    @Mapping(source= "geoCoordinateEntity.lon", target="lon")
    GeoCoordinate entityToDto(GeoCoordinateEntity geoCoordinateEntity);
}
