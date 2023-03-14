package kg.natv.TextAd.services.impl;

import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.models.DTOs.DiscountDTO;
import kg.natv.TextAd.models.DTOs.PriceDTO;
import kg.natv.TextAd.repositories.ChannelRepo;
import kg.natv.TextAd.repositories.PriceRepo;
import kg.natv.TextAd.services.ChannelService;
import kg.natv.TextAd.services.PriceService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepo priceRepo;
    private final ChannelService channelService;
    public PriceServiceImpl(PriceRepo priceRepo, @Lazy ChannelService channelService){
        this.priceRepo = priceRepo;
        this.channelService = channelService;
    }


    @Override
    public PriceDTO calculatePrice(PriceDTO priceDTO) {
        ChannelDTO channel = channelService.findById(priceDTO.getChannelId());
        priceDTO.setPrice(BigDecimal.valueOf(channel.getPricePerLetter()*priceDTO.getDaysCount()*priceDTO.getSymbolCount()));
        return priceDTO;
    }

    @Override
    public PriceDTO calculatePriceWithDiscount(PriceDTO priceDTO) {
        ChannelDTO channel = channelService.findById(priceDTO.getChannelId());
        DiscountDTO discountDTO = new DiscountDTO();
        int maxDays = 0;
        for (DiscountDTO discount : channel.getDiscounts()) {
            if (discount.getFromDayCount() <= priceDTO.getDaysCount()&&discount.getFromDayCount()>maxDays){
                maxDays = discount.getDiscount();
                discountDTO.setDiscount(discount.getDiscount());
            }
        }
        priceDTO.setPriceWithDiscount(priceDTO.getPrice().subtract(priceDTO.getPrice().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(discountDTO.getDiscount()))));
        return priceDTO;
    }
}
