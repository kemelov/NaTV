package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.DTOs.OrderDateDTO;
import kg.natv.TextAd.models.OrderDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderDateMapper {
    OrderDateMapper INSTANCE = Mappers.getMapper(OrderDateMapper.class);
    @Mapping(source = "ad.id", target = "adId")
    OrderDateDTO toDTO(OrderDate orderDate);
    OrderDate toEntity(OrderDateDTO orderDateDTOS);
    List<OrderDateDTO> toDTOList(List<OrderDate> orderDateList);
    List<OrderDate> toEntityList(List<OrderDateDTO> orderDateDTOS);
}
