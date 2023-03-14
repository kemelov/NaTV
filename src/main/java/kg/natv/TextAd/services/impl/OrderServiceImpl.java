package kg.natv.TextAd.services.impl;

import kg.natv.TextAd.mappers.ChannelMapper;
import kg.natv.TextAd.mappers.OrderMapper;
import kg.natv.TextAd.mappers.PriceMapper;
import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.ChannelDateDTO;
import kg.natv.TextAd.models.DTOs.OrderDTO;
import kg.natv.TextAd.models.DTOs.PriceDTO;
import kg.natv.TextAd.models.Order;
import kg.natv.TextAd.models.OrderStatus;
import kg.natv.TextAd.models.Price;
import kg.natv.TextAd.repositories.OrderRepo;
import kg.natv.TextAd.services.ChannelService;
import kg.natv.TextAd.services.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final PriceMapper priceMapper;
    private final ChannelService channelService;
    private final ChannelMapper channelMapper;
    public OrderServiceImpl(OrderRepo orderRepo, OrderMapper orderMapper, PriceMapper priceMapper, ChannelService channelService, ChannelMapper channelMapper){
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.priceMapper = priceMapper;
        this.channelService = channelService;
        this.channelMapper = channelMapper;
    }

    @Transactional
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        PriceDTO priceDTO;
        Ad ad;
        Price price;

        List<Ad> ads = new ArrayList<>();
        List<Price> prices = new ArrayList<>();
        List<ChannelDateDTO> channels = orderDTO.getChannels();
        List<ChannelDateDTO> orderedChannels = new ArrayList<>();

        for (ChannelDateDTO channelDate : channels){
            priceDTO = priceMapper.toPriceDTO(channelDate);
            priceDTO.setText(orderDTO.getText());
            priceDTO = channelService.calculate(priceDTO);

            price = priceMapper.toEntity(priceDTO);
            price.setOrder(order);
            prices.add(price);

            ad = priceMapper.toAd(priceDTO);
            ad.getOrderDate().setDays(priceDTO.getOrderDateDTO().getDays());
            ad.setOrder(order);
            ad.setChannel(channelMapper.toEntity(channelService.findById(priceDTO.getChannelId())));
            ad.getOrderDate().setAd(ad);
            ads.add(ad);

            orderedChannels.add(priceMapper.toChannelDateDTO(priceDTO));
            orderDTO.setTotalPrice(orderDTO.getTotalPrice().add(priceDTO.getPriceWithDiscount()));
        }

        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(OrderStatus.AWAITING);
        order.setCreatedDate(LocalDateTime.now());
        order.setAds(ads);
        order.setPrices(prices);

        order = orderRepo.save(order);
        orderDTO = orderMapper.toDTO(order);
        orderDTO.setChannels(orderedChannels);
        return orderDTO;
    }

    @Override
    public OrderDTO findById(Long id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()){
            return orderMapper.toDTO(order.get());
        } else {
            throw new NoSuchElementException("Не найдено");
        }
    }

    @Override
    public void delete(Long id) {
        findById(id);
        orderRepo.deleteById(id);
        orderRepo.resetAutoIncrement();
    }
}
