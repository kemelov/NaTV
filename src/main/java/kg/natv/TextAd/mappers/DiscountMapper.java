package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.DiscountDTO;
import kg.natv.TextAd.models.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiscountMapper {
    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);
    DiscountDTO ToDTO(Discount discount);
    Discount toEntity(AdDTO discountDTO);
    List<DiscountDTO> toDTOList(List<Discount>discountList);
    List<Discount>toEntityList(List<DiscountDTO>discountDTOList);
}
