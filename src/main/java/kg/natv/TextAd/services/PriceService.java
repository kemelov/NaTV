package kg.natv.TextAd.services;

import kg.natv.TextAd.models.DTOs.PriceDTO;

public interface PriceService {
    PriceDTO calculatePrice(PriceDTO priceDTO);
    PriceDTO calculatePriceWithDiscount(PriceDTO priceDTO);
}
