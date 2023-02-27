package kg.natv.TextAd.mappers;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.models.DTOs.OrderStatusDTO;
import kg.natv.TextAd.models.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderStatusMapper {
    OrderStatusMapper INSTANCE = Mappers.getMapper(OrderStatusMapper.class);
    OrderStatusDTO ToDTO(OrderStatus orderStatus);
    OrderStatus toEntity(OrderStatusDTO orderStatusDTO);
    List<OrderStatusDTO> toDTOList(List<OrderStatus>orderStatusList);
    List<OrderStatus>toEntityList(List<OrderStatusDTO>orderStatusDTOList);
}
