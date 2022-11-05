package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "hopArrival")
public interface HopArrivalMapper {
    HopArrivalMapper INSTANCE = Mappers.getMapper(HopArrivalMapper.class);

    @Mapping(source= "code", target="code")
    @Mapping(source= "description", target="description")
    @Mapping(source= "dateTime", target="dateTime")
    HopArrivalEntity dtoToEntity(HopArrival hopArrival);


    @Mapping(source= "code", target="code")
    @Mapping(source= "description", target="description")
    @Mapping(source= "dateTime", target="dateTime")
    HopArrival entityToDto(HopArrivalEntity hopArrivalEntity);
}
