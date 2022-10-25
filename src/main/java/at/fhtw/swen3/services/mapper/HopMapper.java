package at.fhtw.swen3.services.mapper;


import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HopMapper {
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);

    @Mapping(source= "hopDto.locationName", target="locationName")
    @Mapping(source= "hopDto.locationCoordinates", target="locationCoordinates")
    @Mapping(source= "hopDto.processingDelayMins", target="processingDelayMins")
    @Mapping(source= "hopDto.description", target="description")
    @Mapping(source= "hopDto.code", target="code")
    @Mapping(source= "hopDto.hopType", target="hopType")
    HopEntity dtoToEntity(Hop hopDto);

    @Mapping(source= "hopEntity.locationName", target="locationName")
    @Mapping(source= "hopEntity.locationCoordinates", target="locationCoordinates")
    @Mapping(source= "hopEntity.processingDelayMins", target="processingDelayMins")
    @Mapping(source= "hopEntity.description", target="description")
    @Mapping(source= "hopEntity.code", target="code")
    @Mapping(source= "hopEntity.hopType", target="hopType")
    Hop entityToDto(HopEntity hopEntity);
}
