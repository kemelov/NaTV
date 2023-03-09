package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.*;
import kg.natv.TextAd.models.Channel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"startDate", "endDate", "isActive"})
public class DiscountDTO {
    @JsonIgnore
    private Long id;
    private int discount;
    private int fromDayCount;
    private boolean isActive;
    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy' 'HH:mm:ss")
    private LocalDateTime endDate;
    private Long channelId;

}
