package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.NewParcelInfoEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface NewParcelInfoMapper {
    NewParcelInfoMapper INSTANCE = Mappers.getMapper(NewParcelInfoMapper.class);

    @Mapping(source= "newParcelInfoDto.trackingId", target="trackingId")
    NewParcelInfoEntity dtoToEntity(NewParcelInfo newParcelInfoDto);

    @Mapping(source= "newParcelInfoEntity.trackingId", target="trackingId")
    NewParcelInfo entityToDto(NewParcelInfoEntity newParcelInfoEntity);
}
