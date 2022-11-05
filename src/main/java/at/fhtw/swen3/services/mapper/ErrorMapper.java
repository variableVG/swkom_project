package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.ErrorEntity;
import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.HopArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ErrorMapper {
    ErrorMapper INSTANCE = Mappers.getMapper(ErrorMapper.class);

    @Mapping(source= "errorMessage", target="errorMessage")
    ErrorEntity dtoToEntity(Error errorDto);


    @Mapping(source= "errorMessage", target="errorMessage")
    Error entityToDto(ErrorEntity errorEntity);
}
