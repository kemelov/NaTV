package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@JsonIgnoreProperties(value = {"discounts"}, allowGetters = true)
public class ChannelDTO {
    @JsonIgnore
    private Long id;
    private String name;
    private boolean isActive;
    private String logo;
    private double pricePerLetter;
//    private List<Price> prices;


    private List<DiscountDTO> discounts = new ArrayList<>();
//    private List<Ad> ads;
}
