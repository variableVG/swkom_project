package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TransferwarehouseMapper {
    TransferwarehouseMapper INSTANCE = Mappers.getMapper(TransferwarehouseMapper.class);
    @Mapping(source= "logisticsPartnerUrl", target="logisticsPartnerUrl")
    @Mapping(source= "logisticsPartner", target="logisticsPartner")
    @Mapping(source= "regionGeoJson", target="regionGeoJson")
    TransferwarehouseEntity dtoToEntity(Transferwarehouse transferwarehouseDto);

    @Mapping(source= "logisticsPartnerUrl", target="logisticsPartnerUrl")
    @Mapping(source= "logisticsPartner", target="logisticsPartner")
    @Mapping(source= "regionGeoJson", target="regionGeoJson")
    Transferwarehouse entityToDto(TransferwarehouseEntity transferwarehouseEntity);
}
