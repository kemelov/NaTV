package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class PriceDTO extends AdDTO{
    @JsonIgnore
    private Long priceId;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    @JsonIgnore
    private OrderDTO orderDTO;
}
