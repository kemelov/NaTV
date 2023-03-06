package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.*;
import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.Discount;
import kg.natv.TextAd.models.Price;
import lombok.Data;

import javax.swing.text.View;
import java.util.List;
@Data
@JsonIgnoreProperties(value = { "id", "isActive", "discounts"}, allowGetters = true)
public class ChannelDTO {
    private Long id;
    private String name;
    private boolean isActive;
    private String logo;
    private double pricePerLetter;
//    private List<Price> prices;
    private List<DiscountDTO> discounts;
//    private List<Ad> ads;
}
