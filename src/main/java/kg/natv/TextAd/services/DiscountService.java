package kg.natv.TextAd.services;

import kg.natv.TextAd.models.DTOs.DiscountDTO;

import java.io.IOException;

public interface DiscountService {
    DiscountDTO findById(Long id);

    DiscountDTO update(Long id, DiscountDTO discountDTO) throws IOException;
    DiscountDTO save(DiscountDTO discountDTO);
    void delete(Long id);
    DiscountDTO deactivate(Long id);
}
