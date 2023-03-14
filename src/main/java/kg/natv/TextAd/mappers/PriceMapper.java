package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.ChannelDateDTO;
import kg.natv.TextAd.models.DTOs.PriceDTO;
import kg.natv.TextAd.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
    PriceDTO toDTO(Price price);
    Price toEntity(PriceDTO priceDTO);
    List<PriceDTO> toDTOList(List<Price>priceList);
    List<Price>toEntityList(List<PriceDTO>priceDTOList);


    @Mapping(target = "text", ignore = true)
    PriceDTO toPriceDTO(ChannelDateDTO channelDateDTO);
    ChannelDateDTO toChannelDateDTO(PriceDTO priceDTO);


    @Mapping(source = "orderDateDTO.days", target = "orderDate.days")
    Ad toAd(PriceDTO priceDTO);

}
