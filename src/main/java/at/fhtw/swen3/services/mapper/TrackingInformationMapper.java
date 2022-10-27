package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.TrackingInformationEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackingInformationMapper {
    TrackingInformationMapper INSTANCE = Mappers.getMapper(TrackingInformationMapper.class);
    @Mapping(source= "trackingInformationDto.futureHops", target="futureHops")
    @Mapping(source= "trackingInformationDto.visitedHops", target="visitedHops")
    @Mapping(source= "trackingInformationDto.state", target="state")
    TrackingInformationEntity dtoToEntity(TrackingInformation trackingInformationDto);

    @Mapping(source= "trackingInformationEntity.futureHops", target="futureHops")
    @Mapping(source= "trackingInformationEntity.visitedHops", target="visitedHops")
    @Mapping(source= "trackingInformationEntity.state", target="state")
    TrackingInformation entityToDto(TrackingInformationEntity trackingInformationEntity);
}
