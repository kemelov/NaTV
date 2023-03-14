package kg.natv.TextAd.services;

import kg.natv.TextAd.models.DTOs.OrderDTO;

public interface OrderService {
    OrderDTO save(OrderDTO orderDTO);
    OrderDTO findById(Long id);
    void delete(Long id);
}
