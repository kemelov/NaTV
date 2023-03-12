package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.AdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "channel.id", target = "channelId")
    @Mapping(source = "channel.name", target = "channelName")
    @Mapping(source = "orderDate.days", target = "orderDateDTO.days")
    AdDTO toDTO(Ad ad);
    @Mapping(source = "orderDateDTO.days", target = "orderDate.days")
    Ad toEntity(AdDTO adDTO);
    List<AdDTO> toDTOList(List<Ad>adList);
    List<Ad>toEntityList(List<AdDTO>adDTOList);
    void updateFromDTO(@MappingTarget Ad ad, AdDTO adDTO);
}
