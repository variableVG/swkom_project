package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferwarehouseMapper {
    TransferwarehouseMapper INSTANCE = Mappers.getMapper(TransferwarehouseMapper.class);
    @Mapping(source= "transferwarehouseDto.logisticsPartnerUrl", target="logisticsPartnerUrl")
    @Mapping(source= "transferwarehouseDto.logisticsPartner", target="logisticsPartner")
    @Mapping(source= "transferwarehouseDto.regionGeoJson", target="regionGeoJson")
    TransferwarehouseEntity dtoToEntity(Transferwarehouse transferwarehouseDto);

    @Mapping(source= "transferwarehouseEntity.logisticsPartnerUrl", target="logisticsPartnerUrl")
    @Mapping(source= "transferwarehouseEntity.logisticsPartner", target="logisticsPartner")
    @Mapping(source= "transferwarehouseEntity.regionGeoJson", target="regionGeoJson")
    Transferwarehouse entityToDto(TransferwarehouseEntity transferwarehouseEntity);
}
