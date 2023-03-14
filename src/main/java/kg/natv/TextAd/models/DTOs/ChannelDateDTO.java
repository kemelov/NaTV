package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ChannelDateDTO extends PriceDTO{
    @JsonIgnore
    public String getText(){
        return super.getText();
    }
}
