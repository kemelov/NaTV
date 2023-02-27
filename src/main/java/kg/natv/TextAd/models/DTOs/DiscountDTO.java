package kg.natv.TextAd.models.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.natv.TextAd.models.Channel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiscountDTO {
    private Long id;
    private int discount;
    private int fromDayCount;
    private boolean isActive;
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime endDate;
    private Channel channel;

}
