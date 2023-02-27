package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.AdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdMapper {
    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
    AdDTO ToDTO(Ad ad);
    Ad toEntity(AdDTO adDTO);
    List<AdDTO> toDTOList(List<Ad>adList);
    List<Ad>toEntityList(List<AdDTO>adDTOList);
}
