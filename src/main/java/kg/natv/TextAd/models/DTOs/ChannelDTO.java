package kg.natv.TextAd.models.DTOs;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.Discount;
import kg.natv.TextAd.models.Price;
import lombok.Data;
import java.util.List;

@Data
public class ChannelDTO {
    private Long id;
    private String name;
    private boolean isActive;
    private String logo;
    private double pricePerLetter;
    private List<Price> prices;
    private List<Discount> discounts;
    private List<Ad> ads;
}
