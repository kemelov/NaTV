package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.PriceDTO;
import kg.natv.TextAd.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
    PriceDTO ToDTO(Price price);
    Price toEntity(PriceDTO priceDTO);
    List<PriceDTO> toDTOList(List<Price>priceList);
    List<Price>toEntityList(List<PriceDTO>priceDTOList);
}
